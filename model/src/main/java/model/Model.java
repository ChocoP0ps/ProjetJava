package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import contract.*;
import elements.*;


public class Model extends Observable implements IModel {			//Class model of the MVC design pattern which implements the interface IModel and is an observable object

	private String name;    	//Player's name
	private String map;     	//String of characters which define all elements of the map
	private int level;			//Number of level
	private ArrayList<IElement> elementsList = new ArrayList<IElement>();		//Array which contain all Object of the map
	private ArrayList<Daemon> badList = new ArrayList<Daemon>();				//Array which contain all Daemon of the map
	private ArrayList<Purse> purseList = new ArrayList<Purse>();				//Array which contain all Purse of the map
	private Hero lorann;		//Object Lorann
	private boolean open;		//boolean which show if the level's door is open
	static boolean shooting;	//boolean which show if Lorann's shooting
	private int score;			//Score
	private final DAO dao;		//Object to communicate with the BDD

	public Model(int level) {
		this.dao = new DAO();		//New connection
		this.name = "";				//Name undefined
		this.score = 0;				//Score to 0
		this.level = level;			//We can start at the level that we want
		Model.shooting = false;		//Nobody's shooting
		this.open = false;			//The door is close
		this.loadMap(this.level);	//Load the map in the BDD with the level
		for(int i = 0; i<240;i++){	//Fill the array with empty object
			this.elementsList.add(i,new Empty());
		}
		this.setElements();			//Fill the array with the rights elements
		Thread mouvEnemy = new Thread(new MouvEnemy());							//New thread for the moves of the daemons
		mouvEnemy.start();			//Start of the moves
	}

	public String getMap() {		//Getters of Map
		return this.map;
	}

	public int getLevel(){			//Getters of Level
		return this.level;
	}

	private void setMap(final String map) {		//Setters of Map
		this.map = map;
		this.setChanged();			//Update to the View
		this.notifyObservers();
	}

	public void loadMap(int level) {			//Load the map inside the BDD with the level
		this.level = level;
		this.dao.open();			//Open the connection
		this.setMap(dao.getMap(this.level));
		this.dao.close();			//Close the connection
	}

	public void addName(){			//Add your name in the BDD
		this.dao.open();			//Open the connection
		this.dao.addName(this.name,this.score);
		this.dao.close();			//Close the connection
	}

	public String loadBestName(int place){		//Load the name of the score at the place
		this.dao.open();			//Open the connection
		String bestName = this.dao.getNameBestScore(place);	
		this.dao.close();			//Close the connection
		return bestName;
	}

	public int loadBestScore(int place){		//Load the score of the person at the place
		this.dao.open();			//Open the connection
		int bestScore = this.dao.getBestScore(place);
		this.dao.close();			//Close the connection
		return bestScore;
	}

	public void setElements(){					//Setter of the Array of elements
		if(this.level==2){			//Reset of the score
			this.score = 0;
		}
		char[] elements = this.map.toCharArray();	//Converting the String map in char array
		this.open = false;
		int cpt = 0;				//Verification that there is no 2 times the same daemon
		for(int y = 0; y<12; y++){					//sweep of the map in X and Y
			for (int x =0; x<20; x++){
				switch(elements[x+(20*y)]){			//Switch of the character in the char array of the map
				case 'm' :							//For every letter, set the associated object
					Daemon bad = new Daemon();
					switch(cpt){
					case 0:
						this.elementsList.set(x+(20*y),bad = new Arbarr());		//Setting the object in the case of the array
						bad.setPosX(x);				//Setting the position of the daemon
						bad.setPosY(y);
						this.badList.add(bad);		//Adding the daemon in the daemon array
						break;
					case 1:
						this.elementsList.set(x+(20*y),bad = new Cargyv());		//Setting the object in the case of the array
						bad.setPosX(x);				//Setting the position of the daemon
						bad.setPosY(y);
						this.badList.add(bad);		//Adding the daemon in the daemon array
						break;
					case 2:
						this.elementsList.set(x+(20*y),bad = new Kyracj());		//Setting the object in the case of the array
						bad.setPosX(x);				//Setting the position of the daemon
						bad.setPosY(y);
						this.badList.add(bad);		//Adding the daemon in the daemon array
						break;
					case 3:
						this.elementsList.set(x+(20*y),bad = new Maarcg());		//Setting the object in the case of the array
						bad.setPosX(x);				//Setting the position of the daemon
						bad.setPosY(y);
						this.badList.add(bad);		//Adding the daemon in the daemon array
						break;
					default:
						this.elementsList.set(x+(20*y),new Empty());			//If all the daemon have spawn, set an object empty
						break;
					}
					cpt++;
					break;

				case 'b' :							//For every letter, set the associated object
					this.elementsList.set(x+(20*y),new BoneWall());
					break;
				case 'h' :							//For every letter, set the associated object
					this.elementsList.set(x+(20*y),new HorizontalWall());
					break;
				case 'v' :							//For every letter, set the associated object
					this.elementsList.set(x+(20*y),new VerticalWall());
					break;
				case 'd' :							//For every letter, set the associated object
					this.elementsList.set(x+(20*y),new Door(this.setNextLevel()));
					break;
				case 'n' :							//For every letter, set the associated object
					this.elementsList.set(x+(20*y),new Empty());
					break;
				case 'c' :							//For every letter, set the associated object
					if(this.open){					//Verify is the map is open
						this.elementsList.set(x+(20*y),new Empty());
					}
					else{
						this.elementsList.set(x+(20*y),new CrystalBall());
					}
					break;
				case 's' :							//For every letter, set the associated object
					this.elementsList.set(x+(20*y),this.lorann = new Hero());	//Set the Hero at the spawn in the beginning of the level
					this.lorann.setPosX(x);
					this.lorann.setPosY(y);
					break;
				case 'p' :							//For every letter, set the associated object
					Purse purse;
					this.elementsList.set(x+(20*y),purse = new Purse());
					purse.setPosX(x);
					purse.setPosY(y);
					this.purseList.add(purse);		//Add the purse with its position in the purse's array
				}
			}
		}
	}

	public int setNextLevel(){		//If the player come to a door, setting the next level
		if(this.level == 6)			//If this is the last level, come to the first level
			return 1;
		else
			return (this.level + 1);
	}

	public Hero getLorann() {		//Getter of the hero
		return lorann;
	}

	public boolean Up(Daemon mobile){		//Move the daemon up and return true if there is nothing impenetrable there
		if(this.getElementsList().get(mobile.getPosX() + (mobile.getPosY()-1)*20).getPENETRABLE() == true && getElementsList().get(mobile.getPosX() + (mobile.getPosY()-1)*20).getTYPE() != 4 && getElementsList().get(mobile.getPosX() + (mobile.getPosY()-1)*20).getTYPE() != 6 && getElementsList().get(mobile.getPosX() + (mobile.getPosY()-1)*20).getTYPE() != 12){
			mobile.setPosY(mobile.getPosY() - 1);
			return true;
		}
		else if(this.getElementsList().get(mobile.getPosX() + (mobile.getPosY()-1)*20).getTYPE() == 5){
			this.lorann.setAlive(false);
			return true;
		}
		else{
			return false;
		}
	}

	public boolean Down(Daemon mobile){		//Move the daemon down and return true if there is nothing impenetrable there
		if(this.getElementsList().get(mobile.getPosX() + (mobile.getPosY()+1)*20).getPENETRABLE() == true  && getElementsList().get(mobile.getPosX() + (mobile.getPosY()+1)*20).getTYPE() != 4 && getElementsList().get(mobile.getPosX() + (mobile.getPosY()+1)*20).getTYPE() != 6 && getElementsList().get(mobile.getPosX() + (mobile.getPosY()+1)*20).getTYPE() != 12){
			mobile.setPosY(mobile.getPosY() + 1);
			return true;
		}
		else if(this.getElementsList().get(mobile.getPosX() + (mobile.getPosY()+1)*20).getTYPE() == 5){
			this.lorann.setAlive(false);
			return true;
		}
		else{
			return false;
		}
	}

	public boolean Left(Daemon mobile){		//Move the daemon left and return true if there is nothing impenetrable there
		if(this.getElementsList().get(mobile.getPosX()-1 + (mobile.getPosY())*20).getPENETRABLE() == true && getElementsList().get(mobile.getPosX()-1 + (mobile.getPosY())*20).getTYPE() != 4 && getElementsList().get(mobile.getPosX()-1 + (mobile.getPosY())*20).getTYPE() != 6 && getElementsList().get(mobile.getPosX()-1 + (mobile.getPosY())*20).getTYPE() != 12){
			mobile.setPosX(mobile.getPosX() - 1);
			return true;
		}
		else if(this.getElementsList().get(mobile.getPosX()-1 + (mobile.getPosY())*20).getTYPE() == 5){
			this.lorann.setAlive(false);
			return true;
		}
		else{
			return false;
		}
	}

	public boolean Right(Daemon mobile){		//Move the daemon right and return true if there is nothing impenetrable there
		if(this.getElementsList().get(mobile.getPosX()+1 + (mobile.getPosY())*20).getPENETRABLE() == true && getElementsList().get(mobile.getPosX()+1 + (mobile.getPosY())*20).getTYPE() != 4 && getElementsList().get(mobile.getPosX()+1 + (mobile.getPosY())*20).getTYPE() != 6 && getElementsList().get(mobile.getPosX()+1 + (mobile.getPosY())*20).getTYPE() != 12){
			mobile.setPosX(mobile.getPosX() + 1);
			return true;
		}
		else if(this.getElementsList().get(mobile.getPosX()+1 + (mobile.getPosY())*20).getTYPE() == 5){
			this.lorann.setAlive(false);
			return true;
		}
		else{
			return false;
		}
	}

	public void Up(){		//Move the hero up if there is nothing impenetrable there
		if(this.elementsList.get(this.lorann.getPosX() + (this.lorann.getPosY()-1)*20).getPENETRABLE() == true){
			this.lorann.setPosY(this.lorann.getPosY() - 1);
		}
		else if(this.elementsList.get(this.lorann.getPosX() + (this.lorann.getPosY()-1)*20).getTYPE() == 8 || this.elementsList.get(this.lorann.getPosX() + (this.lorann.getPosY()-1)*20).getTYPE() == 9 || this.elementsList.get(this.lorann.getPosX() + (this.lorann.getPosY()-1)*20).getTYPE() == 10 || this.elementsList.get(this.lorann.getPosX() + (this.lorann.getPosY()-1)*20).getTYPE() == 11){
			this.lorann.setAlive(false);
		}
	}

	public void Down(){		//Move the hero down if there is nothing impenetrable there
		if(this.getElementsList().get(this.lorann.getPosX() + (this.lorann.getPosY()+1)*20).getPENETRABLE() == true){
			this.lorann.setPosY(this.lorann.getPosY() + 1);
		}
		else if(this.getElementsList().get(this.lorann.getPosX() + (this.lorann.getPosY()+1)*20).getTYPE() == 8 || this.getElementsList().get(this.lorann.getPosX() + (this.lorann.getPosY()+1)*20).getTYPE() == 9 || this.getElementsList().get(this.lorann.getPosX() + (this.lorann.getPosY()+1)*20).getTYPE() == 10 || this.getElementsList().get(this.lorann.getPosX() + (this.lorann.getPosY()+1)*20).getTYPE() == 11){
			this.lorann.setAlive(false);
		}
	}

	public void Left(){		//Move the hero left if there is nothing impenetrable there
		if(this.getElementsList().get(this.lorann.getPosX()-1 + (this.lorann.getPosY())*20).getPENETRABLE() == true){
			this.lorann.setPosX(this.lorann.getPosX() - 1);
		}
		else if(this.getElementsList().get(this.lorann.getPosX()-1 + (this.lorann.getPosY())*20).getTYPE() == 8 || this.getElementsList().get(this.lorann.getPosX()-1 + (this.lorann.getPosY())*20).getTYPE() == 9 || this.getElementsList().get(this.lorann.getPosX()-1 + (this.lorann.getPosY())*20).getTYPE() == 10 || this.getElementsList().get(this.lorann.getPosX()-1 + (this.lorann.getPosY())*20).getTYPE() == 11){
			this.lorann.setAlive(false);
		}
	}

	public void Right(){		//Move the hero right if there is nothing impenetrable there
		if(this.getElementsList().get(this.lorann.getPosX()+1 + (this.lorann.getPosY())*20).getPENETRABLE() == true){
			this.lorann.setPosX(this.lorann.getPosX() + 1);
		}
		else if(this.getElementsList().get(this.lorann.getPosX()+1 + (this.lorann.getPosY())*20).getTYPE() == 8 || this.getElementsList().get(this.lorann.getPosX()+1 + (this.lorann.getPosY())*20).getTYPE() == 9 || this.getElementsList().get(this.lorann.getPosX()+1 + (this.lorann.getPosY())*20).getTYPE() == 10 || this.getElementsList().get(this.lorann.getPosX()+1 + (this.lorann.getPosY())*20).getTYPE() == 11){
			this.lorann.setAlive(false);
		}
	}

	public void shoot(char dir){		//Start the thread shoot if the hero is not shooting
		if(Model.shooting == false){
			Thread shot = new Thread(new Shoot(dir));
			shot.start();
		}
	}

	public ArrayList<IElement> getElementsList() {			//Getter of the element's array
		return elementsList;
	}

	public synchronized ArrayList<Daemon> getBadList() {	//Getter synchronized of the daemon's array (only one thread can use this method at a time)
		return badList;
	}

	public void setBadList(ArrayList<Daemon> badList) {		//Setter of the daemon's array
		this.badList = badList;
	}

	public synchronized void modifyArray(){					//Method for every modification of the map
		int newMap = 0;
		char[] elements = this.getMap().toCharArray();
		for(int y = 0; y<12; y++){
			for (int x =0; x<20; x++){						//Sweep of the array in X and Y
				if(x==this.lorann.getPosX() && y == this.lorann.getPosY()){		//Case of the position is the position of Lorann
					if(elements[x+(20*y)] == 'd'){			//If it is a door, load nest level
						this.badList.clear();				//clear the arrays
						this.purseList.clear();
						this.loadMap(((elements.Door) this.elementsList.get(x+(20*y))).getNextLevel());		//load new map
						newMap = 1;
					}
					else if(elements[x+(20*y)] == 'c'){		//If it is a Crystal ball
						this.open = true;					//Open the level
						this.elementsList.set(x+(20*y),this.lorann);
					}
					else if(elements[x+(20*y)] == 'p'){		//If it is a purse
						for(int i = 0; i<this.purseList.size(); i++){		//Find the purse with this position
							if(this.purseList.get(i).getPosX() == this.lorann.getPosX() && this.purseList.get(i).getPosY() == this.lorann.getPosY()){
								if(this.purseList.get(i).isTAKEN() == false){	//Verify if it isn't taken
									this.score = this.score + 100;					//increase the score
									System.out.println("Score : " + this.score);
									this.purseList.get(i).setTAKEN(true);			//Set the state of the purse to TAKEN
									this.elementsList.set(x+(20*y),this.lorann);
								}
							}
						}
					}
					else{	//Else put Lorann
						this.elementsList.set(x+(20*y),this.lorann);
					}

				}
				else{
					switch(elements[x+(20*y)]){
					case 'm' :		//For every letter, set the associated object
						this.elementsList.set(x+(20*y),new Empty());
						break;
					case 'b' :		//For every letter, set the associated object
						this.elementsList.set(x+(20*y),new BoneWall());
						break;
					case 'h' :		//For every letter, set the associated object
						this.elementsList.set(x+(20*y),new HorizontalWall());
						break;
					case 'v' :		//For every letter, set the associated object
						this.elementsList.set(x+(20*y),new VerticalWall());
						break;
					case 'd' :		//For every letter, set the associated object
						this.elementsList.set(x+(20*y),new Door(this.setNextLevel()));
						break;
					case 'n' :		//For every letter, set the associated object
						this.elementsList.set(x+(20*y),new Empty());
						break;
					case 'c' :		//For every letter, set the associated object
						if(this.open){
							this.elementsList.set(x+(20*y),new Empty());
						}
						else{
							this.elementsList.set(x+(20*y),new CrystalBall());
						}
						break;
					case 's' :		//For every letter, set the associated object
						this.elementsList.set(x+(20*y),new Empty());
						break;
					case 'p' :		//For every letter, set the associated object
						this.elementsList.set(x+(20*y),new Empty());
						break;
					}
				}
				for(int i = 0; i<this.badList.size(); i++){			//Put the daemon
					if(this.badList.get(i).isAlive()){				//Verify if they are alive
						this.elementsList.set(this.badList.get(i).getPosX() + (20 * this.badList.get(i).getPosY()), this.badList.get(i));
					}
				}
				for(int i = 0; i<this.purseList.size(); i++){		//Put the purse
					if(this.purseList.get(i).isTAKEN() == false){	//Verify if they aren't taken
						this.elementsList.set(this.purseList.get(i).getPosX() + (20 * this.purseList.get(i).getPosY()), this.purseList.get(i));
					}
				}
			}
		}
		if(this.open){
			for(int w = 0; w<12; w++){
				for (int z =0; z<20;z++){
					if(elements[z+(20*w)] == 'd'){		//Open the door if the Crystal Ball is taken
						this.elementsList.get(z+(20*w)).setPENETRABLE(true);
					}
				}
			}
		}
		if(newMap==1)		//Reload the new map if the hero is on the door
			this.setElements();

		if(this.lorann.isAlive() == false){		//If the hero is dead
			System.out.println("Score : " + this.score);
			this.badList.clear();			//Clear the arrays
			this.purseList.clear();
			this.loadMap(1);				//Load the first map
			this.setElements();
		}
	}

	public String getName() {					//Getter of name
		return name;
	}

	public void setName(String name) {			//Setter of name
		this.name = name;
	}

	public int getScore() {						//Getter of score
		return this.score;
	}

	public void setScore(int score) {			//Setter of score
		this.score = score;
	}

	public boolean isOpen() {					//Getter of the door open
		return open;
	}

	public void setOpen(boolean open) {			//Setter of the door open
		this.open = open;
	}

	public Observable getObservable() {			//Getter of the observable object (the model)
		return this;
	}

	class Shoot implements Runnable{		//Thread of the shoot

		private char dir;				//Direction of shooting
		private int posX;				//Position in X of the FireBall
		private int posY;				//Position in Y of the FireBall
		private boolean recup;			//Boolean which inform if the fireball is recover
		private int levelshoot;			//The level where the fireball was shot

		public Shoot(char dir){
			this.levelshoot = level;		//Set the level of beginning of shooting
			this.dir = dir;					//Set the direction
			this.posX = lorann.getPosX();	//set the position
			this.posY = lorann.getPosY();
			switch(this.dir){				//Switch of the direction
			case 'Z':						//If up
				for(int i = 0; i<getBadList().size(); i++){		//Verify if the next element is a daemon
					if(getBadList().get(i).getPosX()==this.posX && getBadList().get(i).getPosY()==this.posY-1){
						getBadList().remove(i);					//In this case remove it
						score = score + 200;					//And increase the score
						System.out.println("Score : " + score);
						getElementsList().set(this.posX + (this.posY-1)*20, new Empty());
					}
				}
				if(getElementsList().get(this.posX + (this.posY-1)*20).getPENETRABLE() == true && getElementsList().get(this.posX + (this.posY-1)*20).getTYPE() != 4 && getElementsList().get(this.posX + (this.posY-1)*20).getTYPE() != 6 && getElementsList().get(this.posX + (this.posY-1)*20).getTYPE() != 12){			//Verify if the next element is penetrable
					this.posX = lorann.getPosX();				//In this case spawn the fireball at the position
					this.posY = lorann.getPosY()-1;
					this.recup = false;
				}
				else{
					this.recup = true;							//Else don't shoot
				}
				break;
			case 'Q':						//The same for left
				for(int i = 0; i<getBadList().size(); i++){
					if(getBadList().get(i).getPosX()==this.posX-1 && getBadList().get(i).getPosY()==this.posY){
						getBadList().remove(i);
						score = score + 200;
						System.out.println("Score : " + score);
						getElementsList().set(this.posX-1 + (this.posY)*20, new Empty());
					}
				}
				if(getElementsList().get(this.posX-1 + (this.posY)*20).getPENETRABLE() == true && getElementsList().get(this.posX-1 + (this.posY)*20).getTYPE() != 4 && getElementsList().get(this.posX-1 + (this.posY)*20).getTYPE() != 6 && getElementsList().get(this.posX-1 + (this.posY)*20).getTYPE() != 12){
					this.posX = lorann.getPosX()-1;
					this.posY = lorann.getPosY();
					this.recup = false;
				}
				else{
					this.recup = true;
				}
				break;
			case 'S':						//The same for down
				for(int i = 0; i<getBadList().size(); i++){
					if(getBadList().get(i).getPosX()==this.posX && getBadList().get(i).getPosY()==this.posY+1){
						getBadList().remove(i);
						score = score + 200;
						System.out.println("Score : " + score);
						getElementsList().set(this.posX + (this.posY+1)*20, new Empty());
					}
				}
				if(getElementsList().get(this.posX + (this.posY+1)*20).getPENETRABLE() == true && getElementsList().get(this.posX + (this.posY+1)*20).getTYPE() != 4 && getElementsList().get(this.posX + (this.posY+1)*20).getTYPE() != 6 && getElementsList().get(this.posX + (this.posY+1)*20).getTYPE() != 12){
					this.posX = lorann.getPosX();
					this.posY = lorann.getPosY()+1;
					this.recup = false;
				}
				else{
					this.recup = true;
				}
				break;
			case 'D':						//The same for right
				for(int i = 0; i<getBadList().size(); i++){
					if(getBadList().get(i).getPosX()==this.posX+1 && getBadList().get(i).getPosY()==this.posY){
						getBadList().remove(i);
						score = score + 200;
						System.out.println("Score : " + score);
						getElementsList().set(this.posX+1 + (this.posY)*20, new Empty());
					}
				}
				if(getElementsList().get(this.posX+1 + (this.posY)*20).getPENETRABLE() == true && getElementsList().get(this.posX+1 + (this.posY)*20).getTYPE() != 4 && getElementsList().get(this.posX+1 + (this.posY)*20).getTYPE() != 6 && getElementsList().get(this.posX+1 + (this.posY)*20).getTYPE() != 12){
					this.posX = lorann.getPosX()+1;
					this.posY = lorann.getPosY();
					this.recup = false;
				}
				else{
					this.recup = true;
				}
				break;
			}
		}

		public void run() {
			Model.shooting=true;			//Set the model to shooting
			while(this.recup == false && this.levelshoot == level){		//loop until the level is finished or the hero come recover the fireball
				switch(this.dir){			//Switch of the direction
				case 'Z' :					//If up
					for(int i = 0; i<getBadList().size(); i++){			//Verify if the next element is a daemon
						if(getBadList().get(i).getPosX()==this.posX && getBadList().get(i).getPosY()==this.posY-1){
							getBadList().remove(i);						//In this case remove it
							score = score + 200;						//And increase the score
							System.out.println("Score : " + score);
							getElementsList().set(this.posX + (this.posY-1)*20, new Empty());	//remove the fireball of the ancient position
						}
					}
					if(getElementsList().get(this.posX + (this.posY-1)*20).getPENETRABLE() == true && getElementsList().get(this.posX + (this.posY-1)*20).getTYPE() != 4 && getElementsList().get(this.posX + (this.posY-1)*20).getTYPE() != 6 && getElementsList().get(this.posX + (this.posY-1)*20).getTYPE() != 12){			//Verify if the next element is penetrable
						getElementsList().set(this.posX + (this.posY)*20, new Empty());			//remove the fireball of the ancient position
						this.posY--;			//Decrease the position in Y
						getElementsList().set(this.posX + (this.posY)*20, new Fire());			//Put the fireball to the new position
					}
					else{				//If the next element is not penetrable
						for(int i = 0; i<getBadList().size(); i++){
							if(getBadList().get(i).getPosX()==this.posX && getBadList().get(i).getPosY()==this.posY-1){		//If it is a daemon
								getBadList().remove(i);			//Remove it
								score = score + 200;			//And increase the score
								System.out.println("Score : " + score);
								getElementsList().set(this.posX + (this.posY-1)*20, new Empty());		//Put an empty element instead
							}
						}
						if(getElementsList().get(this.posX + (this.posY-1)*20).getTYPE() == 5){			//If it is Lorann
							this.recup = true;					//Recover the fireball
						}
						this.dir = 'S';							//Change the direction to the opposite direction
					}
					break;
				case 'Q' :					//The same for left
					getElementsList().set(this.posX + (this.posY)*20, new Fire());
					for(int i = 0; i<getBadList().size(); i++){
						if(getBadList().get(i).getPosX()==this.posX-1 && getBadList().get(i).getPosY()==this.posY){
							getBadList().remove(i);
							score = score + 200;
							System.out.println("Score : " + score);
							getElementsList().set(this.posX-1 + (this.posY)*20, new Empty());
						}
					}
					if(getElementsList().get(this.posX-1 + (this.posY)*20).getPENETRABLE() == true && getElementsList().get(this.posX-1 + (this.posY)*20).getTYPE() != 4 && getElementsList().get(this.posX-1 + (this.posY)*20).getTYPE() != 6 && getElementsList().get(this.posX-1 + (this.posY)*20).getTYPE() != 12){
						getElementsList().set(this.posX + (this.posY)*20, new Empty());
						this.posX--;
						getElementsList().set(this.posX + (this.posY)*20, new Fire());
					}
					else{
						for(int i = 0; i<getBadList().size(); i++){
							if(getBadList().get(i).getPosX()==this.posX-1 && getBadList().get(i).getPosY()==this.posY){
								getBadList().remove(i);
								score = score + 200;
								System.out.println("Score : " + score);
								getElementsList().set(this.posX-1 + (this.posY)*20, new Empty());
							}
						}
						if(getElementsList().get(this.posX-1 + (this.posY)*20).getTYPE() == 5){
							this.recup = true;
						}
						this.dir = 'D';
					}
					break;
				case 'S' :					//The same for down
					getElementsList().set(this.posX + (this.posY)*20, new Fire());
					for(int i = 0; i<getBadList().size(); i++){
						if(getBadList().get(i).getPosX()==this.posX && getBadList().get(i).getPosY()==this.posY+1){
							getBadList().remove(i);
							score = score + 200;
							System.out.println("Score : " + score);
							getElementsList().set(this.posX + (this.posY+1)*20, new Empty());
						}
					}
					if(getElementsList().get(this.posX + (this.posY+1)*20).getPENETRABLE() == true && getElementsList().get(this.posX + (this.posY+1)*20).getTYPE() != 4 && getElementsList().get(this.posX + (this.posY+1)*20).getTYPE() != 6 && getElementsList().get(this.posX + (this.posY+1)*20).getTYPE() != 12){
						getElementsList().set(this.posX + (this.posY)*20, new Empty());
						this.posY++;
						getElementsList().set(this.posX + (this.posY)*20, new Fire());
					}
					else{
						for(int i = 0; i<getBadList().size(); i++){
							if(getBadList().get(i).getPosX()==this.posX && getBadList().get(i).getPosY()==this.posY+1){
								getBadList().remove(i);
								score = score + 200;
								System.out.println("Score : " + score);
								getElementsList().set(this.posX + (this.posY+1)*20, new Empty());
							}
						}
						if(getElementsList().get(this.posX + (this.posY+1)*20).getTYPE() == 5){
							this.recup = true;
						}
						this.dir = 'Z';
					}
					break;
				case 'D' :					//The same for right
					getElementsList().set(this.posX + (this.posY)*20, new Fire());
					for(int i = 0; i<getBadList().size(); i++){
						if(getBadList().get(i).getPosX()==this.posX+1 && getBadList().get(i).getPosY()==this.posY){
							getBadList().remove(i);
							score = score + 200;
							System.out.println("Score : " + score);
							getElementsList().set(this.posX+1 + (this.posY)*20, new Empty());
						}
					}
					if(getElementsList().get(this.posX+1 + (this.posY)*20).getPENETRABLE() == true && getElementsList().get(this.posX+1 + (this.posY)*20).getTYPE() != 4 && getElementsList().get(this.posX+1 + (this.posY)*20).getTYPE() != 6 && getElementsList().get(this.posX+1 + (this.posY)*20).getTYPE() != 12){
						getElementsList().set(this.posX + (this.posY)*20, new Empty());
						this.posX++;
						getElementsList().set(this.posX + (this.posY)*20, new Fire());
					}
					else{
						for(int i = 0; i<getBadList().size(); i++){
							if(getBadList().get(i).getPosX()==this.posX+1 && getBadList().get(i).getPosY()==this.posY){
								getBadList().remove(i);
								score = score + 200;
								System.out.println("Score : " + score);
								getElementsList().set(this.posX+1 + (this.posY)*20, new Empty());
							}
						}
						if(getElementsList().get(this.posX+1 + (this.posY)*20).getTYPE() == 5){
							this.recup = true;
						}
						this.dir = 'Q';
					}
					break;
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				getElementsList().set(this.posX + (this.posY)*20, new Empty());
			}
			Model.shooting=false;
		}
	}

	class MouvEnemy implements Runnable{			//Thread of the daemon's moves
		private double distance;		//Distance between the daemon and the hero

		public void run() {
			while(true){				//Infinite loop
				for(int i = 0; i < getBadList().size(); i++){		//For every daemon :
					this.distance = Math.sqrt(Math.pow((getBadList().get(i).getPosX() - lorann.getPosX()),2) + Math.pow((getBadList().get(i).getPosY() - lorann.getPosY()), 2));		//Calculation of the distance with the Pythagorean formula
					if(this.distance>4){		//If the distance is superior to 4 block
						i = mouvRandom(i);		//Move the daemon randomly
					}
					else{						//If the distance is inferior to 4 block
						if(Math.abs(getBadList().get(i).getPosX() - lorann.getPosX()) < Math.abs(getBadList().get(i).getPosY() - lorann.getPosY())){		//If the distance between the daemon and the hero is larger in Y than in X
							if(lorann.getPosY() < getBadList().get(i).getPosY()){		//If the position in Y of the hero is bigger than the position in Y of the daemon
								if(Up(getBadList().get(i))){}		//Move up
								else{								//If there is an impenetrable object
									i = mouvRandom(i);				//Move randomly
								}
							}
							else{														//If the position in Y of the daemon is bigger than the position in Y of the hero
								if(Down(getBadList().get(i))){}		//Move down
								else{								//If there is an impenetrable object
									i = mouvRandom(i);				//Move randomly
								}
							}
						}
						else{					//If the distance between the daemon and the hero is larger in X than in Y
							if(lorann.getPosX() < getBadList().get(i).getPosX()){		//If the position in X of the hero is bigger than the position in X of the daemon
								if(Left(getBadList().get(i))){}		//Move left
								else{								//if there is an impenetrable object
									i = mouvRandom(i);				//Move randomly
								}
							}
							else{														//If the position in X of the daemon is bigger than the position in X of the hero
								if(Right(getBadList().get(i))){}	//Move right
								else{								//If there is an impenetrable object
									i = mouvRandom(i);				//Move randomly
								}
							}
						}
					}
					modifyArray();				//Update the element's array
					try {
						Thread.sleep(100);		//Wait 100ms
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

		private int mouvRandom(int i){			//Move the daemon randomly
			Random rand = new Random();
			int rNumber = rand.nextInt(4);		//Generate a random number between 0 and 3
			if(getElementsList().get(getBadList().get(i).getPosX() + (getBadList().get(i).getPosY())*20).getTYPE() != 7){	//If the next element is not the hero
				switch(rNumber){					//Switch of the random number
				case 0:
					if(Up(getBadList().get(i))){}	//Move up
					else{							//if there is an impenetrable object
						i--;						//restart the random move
					}
					break;
				case 1:
					if(Left(getBadList().get(i))){}	//Move left
					else{							//if there is an impenetrable object
						i--;						//restart the random move
					}
					break;
				case 2:
					if(Right(getBadList().get(i))){}//Move right
					else{							//if there is an impenetrable object
						i--;						//restart the random move
					}
					break;
				case 3:
					if(Down(getBadList().get(i))){}	//Move down
					else{							//if there is an impenetrable object
						i--;						//restart the random move
					}
					break;
				}
				modifyArray();			//Update the element's array
			}
			else{											//If the next element is the hero
				getBadList().get(i).setAlive(false);		//Kill him
				modifyArray();			//Update the element's array
			}
			return i;			//return the number which say if we moved
		}
	}
}
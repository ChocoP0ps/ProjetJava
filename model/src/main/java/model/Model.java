package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import contract.IElement;
import contract.IModel;
import elements.*;


public class Model extends Observable implements IModel {
	
	private String name;
	private String map;
	private int level;
	private ArrayList<IElement> elementsList = new ArrayList<IElement>();
	private ArrayList<Deamon> badList = new ArrayList<Deamon>();
	private ArrayList<Purse> purseList = new ArrayList<Purse>();
	private Hero lorann;
	private boolean open;
	static boolean shooting;
	private int score;
	private int first;
	private final DAO dao;
	
	public Model(int level) {
		this.dao = new DAO();
		this.first = 0;
		this.name = "";
		this.score = 0;
		this.level = level;
		Model.shooting = false;
		this.open = false;
		this.loadMap(this.level);
		for(int i = 0; i<240;i++){
			this.elementsList.add(i,new Empty());
		}
		this.setElements();
		Thread mouvEnemy = new Thread(new MouvEnemy());
		mouvEnemy.start();
	}
	
	public String getMap() {
		return this.map;
	}
	
	public int getLevel(){
		return this.level;
	}
	
	private void setMap(final String map) {
		this.map = map;
		this.setChanged();
		this.notifyObservers();
	}

	public void loadMap(int level) {
		this.level = level;
		this.dao.open();
		this.setMap(dao.getMap(this.level));
		this.dao.close();
	}
	
	public void addName(){
		this.dao.open();
		this.dao.addName(this.name,this.score);
		this.dao.close();
	}
	
	public String loadBestName(int place){
		this.dao.open();
		String bestName = this.dao.getNameBestScore(place);
		this.dao.close();
		return bestName;
	}
	
	public int loadBestScore(int place){
		this.dao.open();
		int bestScore = this.dao.getBestScore(place);
		this.dao.close();
		return bestScore;
	}
	
	public void setElements(){
		if(this.level==2){
			this.score = 0;
		}
		char[] elements = this.map.toCharArray();
		this.open = false;
		int cpt = 0;
		for(int y = 0; y<12; y++){
			for (int x =0; x<20; x++){
				switch(elements[x+(20*y)]){
				case 'm' :
					Deamon bad = new Deamon();
					switch(cpt){
					case 0:
						this.elementsList.set(x+(20*y),bad = new Arbarr());
						bad.setPosX(x);
						bad.setPosY(y);
						this.badList.add(bad);
						break;
					case 1:
						this.elementsList.set(x+(20*y),bad = new Cargyv());
						bad.setPosX(x);
						bad.setPosY(y);
						this.badList.add(bad);
						break;
					case 2:
						this.elementsList.set(x+(20*y),bad = new Kyracj());
						bad.setPosX(x);
						bad.setPosY(y);
						this.badList.add(bad);
						break;
					case 3:
						this.elementsList.set(x+(20*y),bad = new Maarcg());
						bad.setPosX(x);
						bad.setPosY(y);
						this.badList.add(bad);
						break;
					default:
						this.elementsList.set(x+(20*y),new Empty());
						break;
					}
					cpt++;
					break;
					
				case 'b' :
					this.elementsList.set(x+(20*y),new BoneWall());
					break;
				case 'h' :
					this.elementsList.set(x+(20*y),new HorizontalWall());
					break;
				case 'v' :
					this.elementsList.set(x+(20*y),new VerticalWall());
					break;
				case 'd' :
					this.elementsList.set(x+(20*y),new Door(this.setNextLevel()));
					break;
				case 'n' :
					this.elementsList.set(x+(20*y),new Empty());
					break;
				case 'c' :
					if(this.open){
						this.elementsList.set(x+(20*y),new Empty());
					}
					else{
						this.elementsList.set(x+(20*y),new CrystalBall());
					}
					break;
				case 's' :
					this.elementsList.set(x+(20*y),this.lorann = new Hero());
					this.lorann.setPosX(x);
					this.lorann.setPosY(y);
					break;
				case 'p' :
					Purse purse;
					this.elementsList.set(x+(20*y),purse = new Purse());
					purse.setPosX(x);
					purse.setPosY(y);
					this.purseList.add(purse);
				}
			}
		}
	}
	
	public int setNextLevel(){
		if(this.level == 6)
			return 1;
		else
			return (this.level + 1);
	}

	public Hero getLorann() {
		return lorann;
	}
	
	public boolean Up(Deamon mobile){
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
	
	public boolean Down(Deamon mobile){
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
	
	public boolean Left(Deamon mobile){
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
	
	public boolean Right(Deamon mobile){
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
	
	public void Up(){
		if(this.elementsList.get(this.lorann.getPosX() + (this.lorann.getPosY()-1)*20).getPENETRABLE() == true){
			this.lorann.setPosY(this.lorann.getPosY() - 1);
		}
		else if(this.elementsList.get(this.lorann.getPosX() + (this.lorann.getPosY()-1)*20).getTYPE() == 8 || this.elementsList.get(this.lorann.getPosX() + (this.lorann.getPosY()-1)*20).getTYPE() == 9 || this.elementsList.get(this.lorann.getPosX() + (this.lorann.getPosY()-1)*20).getTYPE() == 10 || this.elementsList.get(this.lorann.getPosX() + (this.lorann.getPosY()-1)*20).getTYPE() == 11){
			this.lorann.setAlive(false);
		}
	}
	
	public void Down(){
		if(this.getElementsList().get(this.lorann.getPosX() + (this.lorann.getPosY()+1)*20).getPENETRABLE() == true){
			this.lorann.setPosY(this.lorann.getPosY() + 1);
		}
		else if(this.getElementsList().get(this.lorann.getPosX() + (this.lorann.getPosY()+1)*20).getTYPE() == 8 || this.getElementsList().get(this.lorann.getPosX() + (this.lorann.getPosY()+1)*20).getTYPE() == 9 || this.getElementsList().get(this.lorann.getPosX() + (this.lorann.getPosY()+1)*20).getTYPE() == 10 || this.getElementsList().get(this.lorann.getPosX() + (this.lorann.getPosY()+1)*20).getTYPE() == 11){
			this.lorann.setAlive(false);
		}
	}
	
	public void Left(){
		if(this.getElementsList().get(this.lorann.getPosX()-1 + (this.lorann.getPosY())*20).getPENETRABLE() == true){
			this.lorann.setPosX(this.lorann.getPosX() - 1);
		}
		else if(this.getElementsList().get(this.lorann.getPosX()-1 + (this.lorann.getPosY())*20).getTYPE() == 8 || this.getElementsList().get(this.lorann.getPosX()-1 + (this.lorann.getPosY())*20).getTYPE() == 9 || this.getElementsList().get(this.lorann.getPosX()-1 + (this.lorann.getPosY())*20).getTYPE() == 10 || this.getElementsList().get(this.lorann.getPosX()-1 + (this.lorann.getPosY())*20).getTYPE() == 11){
			this.lorann.setAlive(false);
		}
	}
	
	public void Right(){
		if(this.getElementsList().get(this.lorann.getPosX()+1 + (this.lorann.getPosY())*20).getPENETRABLE() == true){
			this.lorann.setPosX(this.lorann.getPosX() + 1);
		}
		else if(this.getElementsList().get(this.lorann.getPosX()+1 + (this.lorann.getPosY())*20).getTYPE() == 8 || this.getElementsList().get(this.lorann.getPosX()+1 + (this.lorann.getPosY())*20).getTYPE() == 9 || this.getElementsList().get(this.lorann.getPosX()+1 + (this.lorann.getPosY())*20).getTYPE() == 10 || this.getElementsList().get(this.lorann.getPosX()+1 + (this.lorann.getPosY())*20).getTYPE() == 11){
			this.lorann.setAlive(false);
		}
	}
	
	public void shoot(char dir){
		if(Model.shooting == false){
			Thread shot = new Thread(new Shoot(dir));
			shot.start();
		}
	}

	public ArrayList<IElement> getElementsList() {
		return elementsList;
	}
	
	public synchronized ArrayList<Deamon> getBadList() {
		return badList;
	}

	public void setBadList(ArrayList<Deamon> badList) {
		this.badList = badList;
	}

	public synchronized void modifyArray(){
		int newMap = 0;
		char[] elements = this.getMap().toCharArray();
		for(int y = 0; y<12; y++){
			for (int x =0; x<20; x++){
				if(x==this.lorann.getPosX() && y == this.lorann.getPosY()){
					if(elements[x+(20*y)] == 'd'){
						this.badList.clear();
						this.purseList.clear();
						this.loadMap(((elements.Door) this.elementsList.get(x+(20*y))).getNextLevel());
						newMap = 1;
					}
					else if(elements[x+(20*y)] == 'c'){
						this.open = true;
						this.elementsList.set(x+(20*y),this.lorann);
					}
					else if(elements[x+(20*y)] == 'p'){
						for(int i = 0; i<this.purseList.size(); i++){
							if(this.purseList.get(i).getPosX() == this.lorann.getPosX() && this.purseList.get(i).getPosY() == this.lorann.getPosY()){
								if(this.purseList.get(i).isTAKEN() == false){
									this.score = this.score + 100;
									System.out.println("Score : " + this.score);
									this.purseList.get(i).setTAKEN(true);
									this.elementsList.set(x+(20*y),this.lorann);
								}
							}
						}
					}
					else{
						this.elementsList.set(x+(20*y),this.lorann);
					}
					
				}
				else{
					switch(elements[x+(20*y)]){
					case 'm' :
						this.elementsList.set(x+(20*y),new Empty());
						break;
					case 'b' :
						this.elementsList.set(x+(20*y),new BoneWall());
						break;
					case 'h' :
						this.elementsList.set(x+(20*y),new HorizontalWall());
						break;
					case 'v' :
						this.elementsList.set(x+(20*y),new VerticalWall());
						break;
					case 'd' :
						this.elementsList.set(x+(20*y),new Door(this.setNextLevel()));
						break;
					case 'n' :
						this.elementsList.set(x+(20*y),new Empty());
						break;
					case 'c' :
						if(this.open){
							this.elementsList.set(x+(20*y),new Empty());
						}
						else{
							this.elementsList.set(x+(20*y),new CrystalBall());
						}
						break;
					case 's' :
						this.elementsList.set(x+(20*y),new Empty());
						break;
					case 'p' :
						this.elementsList.set(x+(20*y),new Empty());
						break;
					}
				}
				for(int i = 0; i<this.badList.size(); i++){
					if(this.badList.get(i).isAlive()){
						this.elementsList.set(this.badList.get(i).getPosX() + (20 * this.badList.get(i).getPosY()), this.badList.get(i));
					}
				}
				for(int i = 0; i<this.purseList.size(); i++){
					if(this.purseList.get(i).isTAKEN() == false){
						this.elementsList.set(this.purseList.get(i).getPosX() + (20 * this.purseList.get(i).getPosY()), this.purseList.get(i));
					}
				}
			}
		}
		if(this.open){
			for(int w = 0; w<12; w++){
				for (int z =0; z<20;z++){
					if(elements[z+(20*w)] == 'd'){
						this.elementsList.get(z+(20*w)).setPENETRABLE(true);
					}
				}
			}
		}
		if(newMap==1)
			this.setElements();
		
		if(this.lorann.isAlive() == false){
			System.out.println("Score : " + this.score);
			this.badList.clear();
			this.purseList.clear();
			this.loadMap(1);
			this.setElements();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public Observable getObservable() {
		return this;
	}
	
class Shoot implements Runnable{
		
		private char dir;
		private int posX;
		private int posY;
		private boolean recup;
		private int levelshoot;
		
		public Shoot(char dir){
			this.levelshoot = level;
			this.dir = dir;
			this.posX = lorann.getPosX();
			this.posY = lorann.getPosY();
			switch(this.dir){
			case 'Z':
				for(int i = 0; i<getBadList().size(); i++){
					if(getBadList().get(i).getPosX()==this.posX && getBadList().get(i).getPosY()==this.posY-1){
						getBadList().remove(i);
						score = score + 200;
						System.out.println("Score : " + score);
						getElementsList().set(this.posX + (this.posY-1)*20, new Empty());
					}
				}
				if(getElementsList().get(this.posX + (this.posY-1)*20).getPENETRABLE() == true && getElementsList().get(this.posX + (this.posY-1)*20).getTYPE() != 4 && getElementsList().get(this.posX + (this.posY-1)*20).getTYPE() != 6 && getElementsList().get(this.posX + (this.posY-1)*20).getTYPE() != 12){
					this.posX = lorann.getPosX();
					this.posY = lorann.getPosY()-1;
					this.recup = false;
				}
				else{
					this.recup = true;
				}
				break;
			case 'Q':
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
			case 'S':
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
			case 'D':
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
			Model.shooting=true;
			while(this.recup == false && this.levelshoot == level){
				switch(this.dir){
				case 'Z' :
					getElementsList().set(this.posX + (this.posY)*20, new Fire());
					for(int i = 0; i<getBadList().size(); i++){
						if(getBadList().get(i).getPosX()==this.posX && getBadList().get(i).getPosY()==this.posY-1){
							getBadList().remove(i);
							score = score + 200;
							System.out.println("Score : " + score);
							getElementsList().set(this.posX + (this.posY-1)*20, new Empty());
						}
					}
					if(getElementsList().get(this.posX + (this.posY-1)*20).getPENETRABLE() == true && getElementsList().get(this.posX + (this.posY-1)*20).getTYPE() != 4 && getElementsList().get(this.posX + (this.posY-1)*20).getTYPE() != 6 && getElementsList().get(this.posX + (this.posY-1)*20).getTYPE() != 12){
						getElementsList().set(this.posX + (this.posY)*20, new Empty());
						this.posY--;
						getElementsList().set(this.posX + (this.posY)*20, new Fire());
					}
					else{
						for(int i = 0; i<getBadList().size(); i++){
							if(getBadList().get(i).getPosX()==this.posX && getBadList().get(i).getPosY()==this.posY-1){
								getBadList().remove(i);
								score = score + 200;
								System.out.println("Score : " + score);
								getElementsList().set(this.posX + (this.posY-1)*20, new Empty());
							}
						}
						if(getElementsList().get(this.posX + (this.posY-1)*20).getTYPE() == 5){
							this.recup = true;
						}
						this.dir = 'S';
					}
					break;
				case 'Q' :
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
				case 'S' :
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
				case 'D' :
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
	
	class MouvEnemy implements Runnable{
		private double distance;

		public void run() {
			while(true){
				for(int i = 0; i < getBadList().size(); i++){
					this.distance = Math.sqrt(Math.pow((getBadList().get(i).getPosX() - lorann.getPosX()),2) + Math.pow((getBadList().get(i).getPosY() - lorann.getPosY()), 2));
					if(this.distance>4){
						i = mouvRandom(i);
					}
					else{
						if(Math.abs(getBadList().get(i).getPosX() - lorann.getPosX()) < Math.abs(getBadList().get(i).getPosY() - lorann.getPosY())){
							if(lorann.getPosY() < getBadList().get(i).getPosY()){
								if(Up(getBadList().get(i))){}
								else{
									i = mouvRandom(i);
								}
							}
							else{
								if(Down(getBadList().get(i))){}
								else{
									i = mouvRandom(i);
								}
							}
						}
						else{
							if(lorann.getPosX() < getBadList().get(i).getPosX()){
								if(Left(getBadList().get(i))){}
								else{
									i = mouvRandom(i);
								}
							}
							else{
								if(Right(getBadList().get(i))){}
								else{
									i = mouvRandom(i);
								}
							}
						}
					}
					modifyArray();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

		private int mouvRandom(int i){
			Random rand = new Random();
			int nombre = rand.nextInt(4);
			if(getElementsList().get(getBadList().get(i).getPosX() + (getBadList().get(i).getPosY())*20).getTYPE() != 7){
				switch(nombre){
				case 0:
					if(Up(getBadList().get(i))){}
					else{
						i--;
					}
					break;
				case 1:
					if(Left(getBadList().get(i))){}
					else{
						i--;
					}
					break;
				case 2:
					if(Right(getBadList().get(i))){}
					else{
						i--;
					}
					break;
				case 3:
					if(Down(getBadList().get(i))){}
					else{
						i--;
					}
					break;
				}
				modifyArray();
			}
			else{
				getBadList().get(i).setAlive(false);
				modifyArray();
			}
			return i;
		}
	}
}
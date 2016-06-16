package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import contract.IElement;
import contract.IModel;
import elements.*;


public class Model extends Observable implements IModel {

	private String map;
	private int level;
	private ArrayList<IElement> elementsList = new ArrayList<IElement>();
	private ArrayList<Deamon> badList = new ArrayList<Deamon>();
	private ArrayList<Purse> purseList = new ArrayList<Purse>();
	private Hero lorann;
	private boolean open;
	static boolean shooting;
	
	public Model(int level) {
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
		final DAO dao = new DAO();
		dao.open();
		this.setMap(dao.getMap(this.level));
		dao.close();
	}
	
	public void setElements(){
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
		if(this.elementsList.get(mobile.getPosX() + (mobile.getPosY()-1)*20).getPENETRABLE() == true && elementsList.get(mobile.getPosX() + (mobile.getPosY()-1)*20).getTYPE() != 4 && elementsList.get(mobile.getPosX() + (mobile.getPosY()-1)*20).getTYPE() != 6 && elementsList.get(mobile.getPosX() + (mobile.getPosY()-1)*20).getTYPE() != 12){
			mobile.setPosY(mobile.getPosY() - 1);
			return true;
		}
		else if(this.elementsList.get(mobile.getPosX() + (mobile.getPosY()-1)*20).getTYPE() == 5){
			this.lorann.setAlive(false);
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean Down(Deamon mobile){
		if(this.elementsList.get(mobile.getPosX() + (mobile.getPosY()+1)*20).getPENETRABLE() == true  && elementsList.get(mobile.getPosX() + (mobile.getPosY()+1)*20).getTYPE() != 4 && elementsList.get(mobile.getPosX() + (mobile.getPosY()+1)*20).getTYPE() != 6 && elementsList.get(mobile.getPosX() + (mobile.getPosY()+1)*20).getTYPE() != 12){
			mobile.setPosY(mobile.getPosY() + 1);
			return true;
		}
		else if(this.elementsList.get(mobile.getPosX() + (mobile.getPosY()+1)*20).getTYPE() == 5){
			this.lorann.setAlive(false);
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean Left(Deamon mobile){
		if(this.elementsList.get(mobile.getPosX()-1 + (mobile.getPosY())*20).getPENETRABLE() == true && elementsList.get(mobile.getPosX()-1 + (mobile.getPosY())*20).getTYPE() != 4 && elementsList.get(mobile.getPosX()-1 + (mobile.getPosY())*20).getTYPE() != 6 && elementsList.get(mobile.getPosX()-1 + (mobile.getPosY())*20).getTYPE() != 12){
			mobile.setPosX(mobile.getPosX() - 1);
			return true;
		}
		else if(this.elementsList.get(mobile.getPosX()-1 + (mobile.getPosY())*20).getTYPE() == 5){
			this.lorann.setAlive(false);
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean Right(Deamon mobile){
		if(this.elementsList.get(mobile.getPosX()+1 + (mobile.getPosY())*20).getPENETRABLE() == true && elementsList.get(mobile.getPosX()+1 + (mobile.getPosY())*20).getTYPE() != 4 && elementsList.get(mobile.getPosX()+1 + (mobile.getPosY())*20).getTYPE() != 6 && elementsList.get(mobile.getPosX()+1 + (mobile.getPosY())*20).getTYPE() != 12){
			mobile.setPosX(mobile.getPosX() + 1);
			return true;
		}
		else if(this.elementsList.get(mobile.getPosX()+1 + (mobile.getPosY())*20).getTYPE() == 5){
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
		if(this.elementsList.get(this.lorann.getPosX() + (this.lorann.getPosY()+1)*20).getPENETRABLE() == true){
			this.lorann.setPosY(this.lorann.getPosY() + 1);
		}
		else if(this.elementsList.get(this.lorann.getPosX() + (this.lorann.getPosY()+1)*20).getTYPE() == 8 || this.elementsList.get(this.lorann.getPosX() + (this.lorann.getPosY()+1)*20).getTYPE() == 9 || this.elementsList.get(this.lorann.getPosX() + (this.lorann.getPosY()+1)*20).getTYPE() == 10 || this.elementsList.get(this.lorann.getPosX() + (this.lorann.getPosY()+1)*20).getTYPE() == 11){
			this.lorann.setAlive(false);
		}
	}
	
	public void Left(){
		if(this.elementsList.get(this.lorann.getPosX()-1 + (this.lorann.getPosY())*20).getPENETRABLE() == true){
			this.lorann.setPosX(this.lorann.getPosX() - 1);
		}
		else if(this.elementsList.get(this.lorann.getPosX()-1 + (this.lorann.getPosY())*20).getTYPE() == 8 || this.elementsList.get(this.lorann.getPosX()-1 + (this.lorann.getPosY())*20).getTYPE() == 9 || this.elementsList.get(this.lorann.getPosX()-1 + (this.lorann.getPosY())*20).getTYPE() == 10 || this.elementsList.get(this.lorann.getPosX()-1 + (this.lorann.getPosY())*20).getTYPE() == 11){
			this.lorann.setAlive(false);
		}
	}
	
	public void Right(){
		if(this.elementsList.get(this.lorann.getPosX()+1 + (this.lorann.getPosY())*20).getPENETRABLE() == true){
			this.lorann.setPosX(this.lorann.getPosX() + 1);
		}
		else if(this.elementsList.get(this.lorann.getPosX()+1 + (this.lorann.getPosY())*20).getTYPE() == 8 || this.elementsList.get(this.lorann.getPosX()+1 + (this.lorann.getPosY())*20).getTYPE() == 9 || this.elementsList.get(this.lorann.getPosX()+1 + (this.lorann.getPosY())*20).getTYPE() == 10 || this.elementsList.get(this.lorann.getPosX()+1 + (this.lorann.getPosY())*20).getTYPE() == 11){
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
	
	public void modifyArray(){
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
								this.purseList.get(i).setTAKEN(true);
								this.elementsList.set(x+(20*y),this.lorann);
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
			this.badList.clear();
			this.purseList.clear();
			this.loadMap(1);
			this.setElements();
		}
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
				for(int i = 0; i<badList.size(); i++){
					if(badList.get(i).getPosX()==this.posX && badList.get(i).getPosY()==this.posY-1 || badList.get(i).getPosX()==this.posX && badList.get(i).getPosY()==this.posY){
						badList.remove(i);
						elementsList.set(this.posX + (this.posY-1)*20, new Empty());
					}
				}
				if(elementsList.get(this.posX + (this.posY-1)*20).getPENETRABLE() == true && elementsList.get(this.posX + (this.posY-1)*20).getTYPE() != 4 && elementsList.get(this.posX + (this.posY-1)*20).getTYPE() != 6 && elementsList.get(this.posX + (this.posY-1)*20).getTYPE() != 12){
					this.posX = lorann.getPosX();
					this.posY = lorann.getPosY()-1;
				}
				else{
					this.recup = true;
				}
				break;
			case 'Q':
				for(int i = 0; i<badList.size(); i++){
					if(badList.get(i).getPosX()==this.posX-1 && badList.get(i).getPosY()==this.posY || badList.get(i).getPosX()==this.posX && badList.get(i).getPosY()==this.posY){
						badList.remove(i);
						elementsList.set(this.posX-1 + (this.posY)*20, new Empty());
					}
				}
				if(elementsList.get(this.posX-1 + (this.posY)*20).getPENETRABLE() == true && elementsList.get(this.posX-1 + (this.posY)*20).getTYPE() != 4 && elementsList.get(this.posX-1 + (this.posY)*20).getTYPE() != 6 && elementsList.get(this.posX-1 + (this.posY)*20).getTYPE() != 12){
					this.posX = lorann.getPosX()-1;
					this.posY = lorann.getPosY();
					this.recup = false;
				}
				else{
					this.recup = true;
				}
				break;
			case 'S':
				for(int i = 0; i<badList.size(); i++){
					if(badList.get(i).getPosX()==this.posX && badList.get(i).getPosY()==this.posY+1 || badList.get(i).getPosX()==this.posX && badList.get(i).getPosY()==this.posY){
						badList.remove(i);
						elementsList.set(this.posX + (this.posY+1)*20, new Empty());
					}
				}
				if(elementsList.get(this.posX + (this.posY+1)*20).getPENETRABLE() == true && elementsList.get(this.posX + (this.posY+1)*20).getTYPE() != 4 && elementsList.get(this.posX + (this.posY+1)*20).getTYPE() != 6 && elementsList.get(this.posX + (this.posY+1)*20).getTYPE() != 12){
					this.posX = lorann.getPosX();
					this.posY = lorann.getPosY()+1;
					this.recup = false;
				}
				else{
					this.recup = true;
				}
				break;
			case 'D':
				for(int i = 0; i<badList.size(); i++){
					if(badList.get(i).getPosX()==this.posX+1 && badList.get(i).getPosY()==this.posY || badList.get(i).getPosX()==this.posX && badList.get(i).getPosY()==this.posY){
						badList.remove(i);
						elementsList.set(this.posX+1 + (this.posY)*20, new Empty());
					}
				}
				if(elementsList.get(this.posX+1 + (this.posY)*20).getPENETRABLE() == true && elementsList.get(this.posX+1 + (this.posY)*20).getTYPE() != 4 && elementsList.get(this.posX+1 + (this.posY)*20).getTYPE() != 6&& elementsList.get(this.posX+1 + (this.posY)*20).getTYPE() != 12){
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

		public synchronized void run() {
			Model.shooting=true;
			while(this.recup == false && this.levelshoot == level){
				switch(this.dir){
				case 'Z' :
					elementsList.set(this.posX + (this.posY)*20, new Fire());
					for(int i = 0; i<badList.size(); i++){
						if(badList.get(i).getPosX()==this.posX && badList.get(i).getPosY()==this.posY-1 || badList.get(i).getPosX()==this.posX && badList.get(i).getPosY()==this.posY){
							badList.remove(i);
							elementsList.set(this.posX + (this.posY-1)*20, new Empty());
						}
					}
					if(elementsList.get(this.posX + (this.posY-1)*20).getPENETRABLE() == true && elementsList.get(this.posX + (this.posY-1)*20).getTYPE() != 4 && elementsList.get(this.posX + (this.posY-1)*20).getTYPE() != 6 && elementsList.get(this.posX + (this.posY-1)*20).getTYPE() != 12){
						elementsList.set(this.posX + (this.posY)*20, new Empty());
						this.posY--;
						elementsList.set(this.posX + (this.posY)*20, new Fire());
					}
					else{
						if(elementsList.get(this.posX + (this.posY-1)*20).getTYPE() == 5){
							this.recup = true;
						}
						this.dir = 'S';
					}
					break;
				case 'Q' :
					elementsList.set(this.posX + (this.posY)*20, new Fire());
					for(int i = 0; i<badList.size(); i++){
						if(badList.get(i).getPosX()==this.posX-1 && badList.get(i).getPosY()==this.posY || badList.get(i).getPosX()==this.posX && badList.get(i).getPosY()==this.posY){
							badList.remove(i);
							elementsList.set(this.posX-1 + (this.posY)*20, new Empty());
						}
					}
					if(elementsList.get(this.posX-1 + (this.posY)*20).getPENETRABLE() == true && elementsList.get(this.posX-1 + (this.posY)*20).getTYPE() != 4 && elementsList.get(this.posX-1 + (this.posY)*20).getTYPE() != 6 && elementsList.get(this.posX-1 + (this.posY)*20).getTYPE() != 12){
						elementsList.set(this.posX + (this.posY)*20, new Empty());
						this.posX--;
						elementsList.set(this.posX + (this.posY)*20, new Fire());
					}
					else{
						if(elementsList.get(this.posX-1 + (this.posY)*20).getTYPE() == 5){
							this.recup = true;
						}
						this.dir = 'D';
					}
					break;
				case 'S' :
					elementsList.set(this.posX + (this.posY)*20, new Fire());
					for(int i = 0; i<badList.size(); i++){
						if(badList.get(i).getPosX()==this.posX && badList.get(i).getPosY()==this.posY+1 || badList.get(i).getPosX()==this.posX && badList.get(i).getPosY()==this.posY){
							badList.remove(i);
							elementsList.set(this.posX + (this.posY+1)*20, new Empty());
						}
					}
					if(elementsList.get(this.posX + (this.posY+1)*20).getPENETRABLE() == true && elementsList.get(this.posX + (this.posY+1)*20).getTYPE() != 4 && elementsList.get(this.posX + (this.posY+1)*20).getTYPE() != 6 && elementsList.get(this.posX + (this.posY+1)*20).getTYPE() != 12){
						elementsList.set(this.posX + (this.posY)*20, new Empty());
						this.posY++;
						elementsList.set(this.posX + (this.posY)*20, new Fire());
					}
					else{
						if(elementsList.get(this.posX + (this.posY+1)*20).getTYPE() == 5){
							this.recup = true;
						}
						this.dir = 'Z';
					}
					break;
				case 'D' :
					elementsList.set(this.posX + (this.posY)*20, new Fire());
					for(int i = 0; i<badList.size(); i++){
						if(badList.get(i).getPosX()==this.posX+1 && badList.get(i).getPosY()==this.posY || badList.get(i).getPosX()==this.posX && badList.get(i).getPosY()==this.posY){
							badList.remove(i);
							elementsList.set(this.posX+1 + (this.posY)*20, new Empty());
						}
					}
					if(elementsList.get(this.posX+1 + (this.posY)*20).getPENETRABLE() == true && elementsList.get(this.posX+1 + (this.posY)*20).getTYPE() != 4 && elementsList.get(this.posX+1 + (this.posY)*20).getTYPE() != 6 && elementsList.get(this.posX+1 + (this.posY)*20).getTYPE() != 12){
						elementsList.set(this.posX + (this.posY)*20, new Empty());
						this.posX++;
						elementsList.set(this.posX + (this.posY)*20, new Fire());
					}
					else{
						if(elementsList.get(this.posX+1 + (this.posY)*20).getTYPE() == 5){
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
				elementsList.set(this.posX + (this.posY)*20, new Empty());
			}
			Model.shooting=false;
		}
	}
	
	class MouvEnemy implements Runnable{

		public void run() {
			while(true){
				for(int i = 0; i < badList.size(); i++){
					Random rand = new Random();
					int nombre = rand.nextInt(4);
					if(elementsList.get(badList.get(i).getPosX() + (badList.get(i).getPosY())*20).getTYPE() != 7){
						switch(nombre){
						case 0:
							if(Up(badList.get(i))){}
							else{
								i--;
							}
							break;
						case 1:
							if(Left(badList.get(i))){}
							else{
								i--;
							}
							break;
						case 2:
							if(Right(badList.get(i))){}
							else{
								i--;
							}
							break;
						case 3:
							if(Down(badList.get(i))){}
							else{
								i--;
							}
							break;
						}
					}
					else{
						badList.get(i).setAlive(false);
					}
				}
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				modifyArray();
			}
		}
	}
}

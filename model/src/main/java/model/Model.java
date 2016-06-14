package model;

import java.util.ArrayList;
import java.util.Observable;

import contract.IElement;
import contract.IModel;
import elements.*;


public class Model extends Observable implements IModel {

	private String map;
	private int level;
	private ArrayList<IElement> elementsList = new ArrayList<IElement>();
	private Hero lorann;
	private boolean open;
	
	public Model(int level) {
		this.level = level;
		this.open = false;
		this.loadMap(this.level);
		for(int i = 0; i<240;i++){
			this.elementsList.add(i,new Empty());
		}
		this.setElements();
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
		for(int y = 0; y<12; y++){
			for (int x =0; x<20; x++){
				switch(elements[x+(20*y)]){
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
	
	public void Up(){
		if(this.elementsList.get(this.lorann.getPosX() + (this.lorann.getPosY()-1)*20).getPENETRABLE() == true){
			this.lorann.setPosY(this.lorann.getPosY() - 1);
		}
	}
	
	public void Down(){
		if(this.elementsList.get(this.lorann.getPosX() + (this.lorann.getPosY()+1)*20).getPENETRABLE() == true){
			this.lorann.setPosY(this.lorann.getPosY() + 1);
		}
	}
	
	public void Left(){
		if(this.elementsList.get(this.lorann.getPosX()-1 + (this.lorann.getPosY())*20).getPENETRABLE() == true){
			this.lorann.setPosX(this.lorann.getPosX() - 1);
		}
	}
	
	public void Right(){
		if(this.elementsList.get(this.lorann.getPosX()+1 + (this.lorann.getPosY())*20).getPENETRABLE() == true){
			this.lorann.setPosX(this.lorann.getPosX() + 1);
		}
	}
	
	public void shoot()
	{
		
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
						this.loadMap(((elements.Door) this.elementsList.get(x+(20*y))).getNextLevel());
						newMap = 1;
					}
					else if(elements[x+(20*y)] == 'c'){
						this.open = true;
					}
					else{
						this.elementsList.set(x+(20*y),this.lorann);
					}
				}
				else{
					switch(elements[x+(20*y)]){
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
}

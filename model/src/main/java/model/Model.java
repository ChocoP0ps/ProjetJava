package model;

import java.util.ArrayList;
import java.util.Observable;

import contract.IModel;
import elements.*;

/**
 * The Class Model.
 *
 * @author Jean-Aymeric Diet
 */
public class Model extends Observable implements IModel {

	private String map;
	private ArrayList<Element> elementsList = new ArrayList<Element>();
	private Hero lorann;
	
	public Model() {
		this.loadMap(1);
		for(int i = 0; i<240;i++){
			this.elementsList.add(i,new Empty());
		}
		this.setElements();
	}
	
	public String getMap() {
		return this.map;
	}
	
	private void setMap(final String map) {
		this.map = map;
		this.setChanged();
		this.notifyObservers();
	}

	public void loadMap(int level) {
		final DAO dao = new DAO();
		dao.open();
		this.setMap(dao.getMap(level));
		dao.close();
	}
	
	public void setElements(){
		char[] elements = this.getMap().toCharArray();
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
					this.elementsList.set(x+(20*y),new Door());
					break;
				case 'n' :
					this.elementsList.set(x+(20*y),new Empty());
					break;
				case 's' :
					this.elementsList.set(x+(20*y),lorann = new Hero());
					lorann.setPosX(x);
					lorann.setPosY(y);
					break;
				}
			}
		}
	}

	public Hero getLorann() {
		return lorann;
	}
	
	public void Up(Hero lorann){
		if(this.elementsList.get(lorann.getPosX() + (lorann.getPosY()-1)*20).getPENETRABLE() == true){
			lorann.setPosY(lorann.getPosY() - 1);
		}
	}
	
	public void Down(Hero lorann){
		if(this.elementsList.get(lorann.getPosX() + (lorann.getPosY()+1)*20).getPENETRABLE() == true){
			lorann.setPosY(lorann.getPosY() + 1);
		}
	}
	
	public void Left(Hero lorann){
		if(this.elementsList.get(lorann.getPosX()-1 + (lorann.getPosY())*20).getPENETRABLE() == true){
			lorann.setPosX(lorann.getPosX() - 1);
		}
	}
	
	public void Right(Hero lorann){
		if(this.elementsList.get(lorann.getPosX()+1 + (lorann.getPosY())*20).getPENETRABLE() == true){
			lorann.setPosX(lorann.getPosX() + 1);
		}
	}

	public ArrayList<Element> getElementsList() {
		return elementsList;
	}
	
	public void modifyArray(Hero lorann){
		char[] elements = this.getMap().toCharArray();
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
					this.elementsList.set(x+(20*y),new Door());
					break;
				case 'n' :
					this.elementsList.set(x+(20*y),new Empty());
					break;
				case 's' :
					this.elementsList.set(x+(20*y),new Empty());
					break;
				}
				if(x==lorann.getPosX() && y == lorann.getPosY()){
					this.elementsList.set(x+(20*y),lorann = new Hero());
				}
			}
		}
	}

	public Observable getObservable() {
		return this;
	}
}

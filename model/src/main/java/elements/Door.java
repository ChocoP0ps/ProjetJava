package elements;

public class Door extends Element{ //The class door extend from element
	private int TYPE = 4; //the "type" is used to associate a class with a sprite
	private boolean PENETRABLE; //Boolean that correspond to the penetrability of an element
	private int nextLevel; // Variable that store the id of next level
	

	public Door(int nextLevel) {
		super();
		this.PENETRABLE = false; //This item can't be penetrated by any entity
		this.nextLevel = nextLevel; 
	}

	public boolean getPENETRABLE() { //getters of the penetration of the element
		return PENETRABLE;
	}

	public void setPENETRABLE(boolean penetrable) { //setters of the penetration of the element
		PENETRABLE = penetrable;
	}

	public int getTYPE() { //Getters of the Type of the element
		return TYPE;
	}

	public void setTYPE(int type) { //Setters of the Type of the element
		TYPE = type;
	}

	public int getNextLevel() { //Getters of the next level
		return nextLevel;
	}

	public void setNextLevel(int nextLevel) { //setters of the next level
		this.nextLevel = nextLevel;
	}
}

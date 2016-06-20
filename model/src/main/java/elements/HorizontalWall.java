package elements;

public class HorizontalWall extends Wall{ //The class extends from Wall
	private int TYPE = 2; //the "type" is used to associate a class with a sprite
	private boolean PENETRABLE = false; //The element can't be penetrated by any entity
	

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
}

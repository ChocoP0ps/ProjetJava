package elements;

public class VerticalWall extends Wall{ //The class extends from Wall
	private int TYPE = 3; //the "type" is used to associate a class with a sprite
	private boolean PENETRABLE = false; //Boolean variable that correspond to the penetration of the element
	

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

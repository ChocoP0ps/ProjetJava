package elements;

public class CrystalBall extends Element{ //CrystalBall extends from the class Element
	private int TYPE = 6;  //the "type" is used to associate a class with a sprite
	private boolean PENETRABLE = true; //The element can't be penetrated by any entity
	

	public boolean getPENETRABLE() { //getters of the penetration of the element
		return PENETRABLE;
	}

	public void setPENETRABLE(boolean penetrable) { //setters of the penetration of the element
		PENETRABLE = penetrable;
	}

	public int getTYPE() { //getters of the type of the element
		return TYPE;
	}

	public void setTYPE(int type) { //setters of the type
		TYPE = type;
	}
}


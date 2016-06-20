package elements;

public class Empty extends Element{ //The class extends from Element
	private int TYPE = 0; //the "type" is used to associate a class with a sprite
	private boolean PENETRABLE = true; //The element can be penetrated 
	

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

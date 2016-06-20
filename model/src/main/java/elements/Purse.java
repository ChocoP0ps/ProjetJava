package elements;

public class Purse extends Element{ //The class extends from Element
	private int TYPE = 12; //the "type" is used to associate a class with a sprite
	private boolean PENETRABLE = true; //The element can be penetrated by a entity
	private boolean TAKEN = false; //When the Purse is pickup
	private int PosX; //Variable that corresponds to the x-axis position 
	private int PosY; //Variable that corresponds to the y-axis position
	
	public int getPosX() { //Getters of the abscissa position
		return PosX;
	}
	
	public void setPosX(int posX) { //Setters of the abscissa position
		PosX = posX;
	}
	
	public int getPosY() { //Getters of the Ordinate position
		return PosY;
	}
	
	public void setPosY(int posY) { //Setters of the abscissa position
		PosY = posY;
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

	public boolean isTAKEN() { //Getters of taken
		return TAKEN;
	}

	public void setTAKEN(boolean tAKEN) { //setters of taken
		TAKEN = tAKEN;
	}
}


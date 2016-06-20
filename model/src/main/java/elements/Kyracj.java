package elements;

public class Kyracj extends Daemon { //The class extends from Daemon
	private int TYPE = 8; //the "type" is used to associate a class with a sprite
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
	
	public int getTYPE() { //Getters of the Type of the element
		return TYPE;
	}

	public void setTYPE(int type) { //Setters of the Type of the element
		TYPE = type;
	}
}

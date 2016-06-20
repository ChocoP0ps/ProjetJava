package elements;

public class Arbarr extends Daemon{ // The class Arbarr extends from daemons. Arbarr is one of the fourth monster in the game
	private int TYPE = 10; //the "type" is used to associate a class with a sprite
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
	
	public int getTYPE() { //Getters of the type
		return TYPE;
	}

	public void setTYPE(int type) { //Setters of the type
		TYPE = type;
	}
}

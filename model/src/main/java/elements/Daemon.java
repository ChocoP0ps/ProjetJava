package elements;

public class Daemon extends Mobile { //The class Daemon extends from Mobile

	private boolean PENETRABLE = false; //Boolean variable that correspond to the penetration of the element
	private int PosX; //Variable that corresponds to the x-axis position 
	private int PosY; //Variable that corresponds to the y-axis position


	public boolean getPENETRABLE() { //getters of the penetration of the element
		return PENETRABLE;
	}

	public void setPENETRABLE(boolean penetrable) { //setters of the penetration of the element
		PENETRABLE = penetrable;
	}
	
	public int getPosX() { //Getters of the x-axis position  
		return PosX;
	}
	
	public void setPosX(int posX) { //setters of the x-axis position 
		PosX = posX;
	}
	
	public int getPosY() { //Getters of the y-axis position  
		return PosY;
	}
	
	public void setPosY(int posY) { //Setters of the y-axis position  
		PosY = posY;
	}
}

package elements;

public class Mobile extends Element{ //The class extends from Element

	public Mobile() {
		// TODO Auto-generated constructor stub
	}
	private int PosX; //Variable that corresponds to the x-axis position 
	private int PosY; //Variable that corresponds to the y-axis position
	private boolean alive = true;
	
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

	public boolean isAlive() { // The element is alive or not
		return alive;
	}

	public void setAlive(boolean alive) { //Allow to set true or false for the variable Alive
		this.alive = alive;
	}
}

package elements;

// TODO: Auto-generated Javadoc
/**
 * The Class Mobile.
 */
public class Mobile extends Element{ //The class extends from Element

	/**
  * Instantiates a new mobile.
  */
 public Mobile() {
		// TODO Auto-generated constructor stub
	}
	
	/** The Pos X. */
	private int PosX; //Variable that corresponds to the x-axis position 
	
	/** The Pos Y. */
	private int PosY; //Variable that corresponds to the y-axis position
	
	/** The alive. */
	private boolean alive = true;
	
	/**
	 * Gets the pos X.
	 *
	 * @return the pos X
	 */
	public int getPosX() { //Getters of the abscissa position
		return PosX;
	}
	
	/**
	 * Sets the pos X.
	 *
	 * @param posX the new pos X
	 */
	public void setPosX(int posX) { //Setters of the abscissa position
		PosX = posX;
	}
	
	/**
	 * Gets the pos Y.
	 *
	 * @return the pos Y
	 */
	public int getPosY() { //Getters of the Ordinate position
		return PosY;
	}
	
	/**
	 * Sets the pos Y.
	 *
	 * @param posY the new pos Y
	 */
	public void setPosY(int posY) { //Setters of the abscissa position
		PosY = posY;
	}

	/**
	 * Checks if is alive.
	 *
	 * @return true, if is alive
	 */
	public boolean isAlive() { // The element is alive or not
		return alive;
	}

	/**
	 * Sets the alive.
	 *
	 * @param alive the new alive
	 */
	public void setAlive(boolean alive) { //Allow to set true or false for the variable Alive
		this.alive = alive;
	}
}

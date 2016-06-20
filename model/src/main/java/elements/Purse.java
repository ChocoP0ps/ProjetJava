package elements;

// TODO: Auto-generated Javadoc
/**
 * The Class Purse.
 */
@SuppressWarnings("unused")
public class Purse extends Element{ /** The type. */
 //The class extends from Element
	private int TYPE = 12; //the "type" is used to associate a class with a sprite
	
	/** The penetrable. */
	private boolean PENETRABLE = true; //The element can be penetrated by a entity
	
	/** The taken. */
	private boolean TAKEN = false; //When the Purse is pickup
	
	/** The Pos X. */
	private int PosX; //Variable that corresponds to the x-axis position 
	
	/** The Pos Y. */
	private int PosY; //Variable that corresponds to the y-axis position
	
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
	 * Checks if is taken.
	 *
	 * @return true, if is taken
	 */
	public boolean isTAKEN() { //Getters of taken
		return TAKEN;
	}

	/**
	 * Sets the taken.
	 *
	 * @param tAKEN the new taken
	 */
	public void setTAKEN(boolean tAKEN) { //setters of taken
		TAKEN = tAKEN;
	}
}


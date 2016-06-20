package elements;

// TODO: Auto-generated Javadoc
/**
 * The Class Door.
 */
@SuppressWarnings("unused")
public class Door extends Element{ /** The type. */
 //The class door extend from element
	private int TYPE = 4; //the "type" is used to associate a class with a sprite
	
	/** The penetrable. */
	private boolean PENETRABLE; //Boolean that correspond to the penetrability of an element
	
	/** The next level. */
	private int nextLevel; // Variable that store the id of next level
	

	/**
	 * Instantiates a new door.
	 *
	 * @param nextLevel the next level
	 */
	public Door(int nextLevel) {
		super();
		this.PENETRABLE = false; //This item can't be penetrated by any entity
		this.nextLevel = nextLevel; 
	}

	/**
	 * Gets the next level.
	 *
	 * @return the next level
	 */
	public int getNextLevel() { //Getters of the next level
		return nextLevel;
	}

	/**
	 * Sets the next level.
	 *
	 * @param nextLevel the new next level
	 */
	public void setNextLevel(int nextLevel) { //setters of the next level
		this.nextLevel = nextLevel;
	}
}

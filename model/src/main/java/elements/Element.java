package elements;

import contract.IElement;

// TODO: Auto-generated Javadoc
/**
 * The Class Element.
 */
public abstract class Element implements IElement{ /** The type. */
 //The class element implements the interface IElement
	private int TYPE; //the "type" is used to associate a class with a sprite
	
	/** The penetrable. */
	private boolean PENETRABLE; //Boolean that correspond to the penetrability of an element

	/* (non-Javadoc)
	 * @see contract.IElement#getPENETRABLE()
	 */
	public boolean getPENETRABLE() { //getters of the penetration of the element
		return PENETRABLE;
	}

	/* (non-Javadoc)
	 * @see contract.IElement#setPENETRABLE(boolean)
	 */
	public void setPENETRABLE(boolean penetrable) { //setters of the penetration of the element
		PENETRABLE = penetrable;
	}

	/* (non-Javadoc)
	 * @see contract.IElement#getTYPE()
	 */
	public int getTYPE() { //Getters of the Type of the element
		return TYPE;
	}

	/* (non-Javadoc)
	 * @see contract.IElement#setTYPE(int)
	 */
	public void setTYPE(int type) { //Setters of the Type of the element
		TYPE = type;
	}
}

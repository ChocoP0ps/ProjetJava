package elements;

import contract.IElement;

public abstract class Element implements IElement{ //The class element implements the interface IElement
	private int TYPE; //the "type" is used to associate a class with a sprite
	private boolean PENETRABLE; //Boolean that correspond to the penetrability of an element

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

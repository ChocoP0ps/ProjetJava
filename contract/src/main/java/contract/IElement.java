package contract;

// TODO: Auto-generated Javadoc
/**
 * The Interface IElement.
 */
public interface IElement {				//Interface implemented by the elements of the element's array
	
	/**
				 * Gets the penetrable.
				 *
				 * @return the penetrable
				 */
				public boolean getPENETRABLE();							//Getter of penetrability

	/**
	 * Sets the penetrable.
	 *
	 * @param penetrable the new penetrable
	 */
	public void setPENETRABLE(boolean penetrable);			//Setter of penetrability

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public int getTYPE();									//Getter of the element's type

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setTYPE(int type);							//Setter of the element's type
}

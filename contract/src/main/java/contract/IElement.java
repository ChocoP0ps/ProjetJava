package contract;

public interface IElement {				//Interface implemented by the elements of the element's array
	
	public boolean getPENETRABLE();							//Getter of penetrability

	public void setPENETRABLE(boolean penetrable);			//Setter of penetrability

	public int getTYPE();									//Getter of the element's type

	public void setTYPE(int type);							//Setter of the element's type
}

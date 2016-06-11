package elements;

public class Empty extends Element{
	private String TYPE = "Empty";
	private boolean PENETRABLE = true;
	

	public boolean getPENETRABLE() {
		return PENETRABLE;
	}

	public void setPENETRABLE(boolean penetrable) {
		PENETRABLE = penetrable;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String type) {
		TYPE = type;
	}
	
}

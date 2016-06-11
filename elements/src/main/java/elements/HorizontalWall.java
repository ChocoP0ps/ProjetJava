package elements;

public class HorizontalWall extends Wall{
	private String TYPE = "Horizontal Wall";
	private boolean PENETRABLE = false;
	

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

package elements;

public abstract class Element {
	private String TYPE = "";
	private boolean PENETRABLE;

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

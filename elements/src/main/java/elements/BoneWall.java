package elements;

public class BoneWall extends Wall{
	private int TYPE = 1;
	private boolean PENETRABLE = false;
	

	public boolean getPENETRABLE() {
		return PENETRABLE;
	}

	public void setPENETRABLE(boolean penetrable) {
		PENETRABLE = penetrable;
	}

	public int getTYPE() {
		return TYPE;
	}

	public void setTYPE(int type) {
		TYPE = type;
	}
}

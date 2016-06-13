package elements;

public class Door extends Element{
	private int TYPE = 4;
	private boolean PENETRABLE = true;
	private int nextLevel;
	

	public Door(int nextLevel) {
		super();
		this.nextLevel = nextLevel;
	}

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

	public int getNextLevel() {
		return nextLevel;
	}

	public void setNextLevel(int nextLevel) {
		this.nextLevel = nextLevel;
	}
}

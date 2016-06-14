package elements;

public class fire extends Element {
	private int TYPE = 7;
	private boolean PENETRABLE = true;
	private int PosX;
	private int PosY;
	
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
	
	public int getPosX() {
		return PosX;
	}
	
	public void setPosX(int posX) {
		PosX = posX;
	}
	
	public int getPosY() {
		return PosY;
	}
	
	public void setPosY(int posY) {
		PosY = posY;
	}
}


}

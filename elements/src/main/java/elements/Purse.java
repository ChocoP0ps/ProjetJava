package elements;

public class Purse extends Element{
	private int TYPE = 12;
	private boolean PENETRABLE = true;
	private boolean TAKEN = false;
	private int PosX;
	private int PosY;
	
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

	public boolean isTAKEN() {
		return TAKEN;
	}

	public void setTAKEN(boolean tAKEN) {
		TAKEN = tAKEN;
	}
}


package elements;

public class Arbarr extends Daemon{
	private int TYPE = 10;
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
	
	public int getTYPE() {
		return TYPE;
	}

	public void setTYPE(int type) {
		TYPE = type;
	}
}

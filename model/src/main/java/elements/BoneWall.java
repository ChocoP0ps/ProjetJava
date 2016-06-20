package elements;

public class BoneWall extends Wall{ //The classe bonewall extends from Wall
	private int TYPE = 1; // //the "type" is used to associate a class with a sprite
	private boolean PENETRABLE = false; //The element can't be penetrated by any entity
	

	public boolean getPENETRABLE() { //getters of the penetration of the element
		return PENETRABLE;
	}

	public void setPENETRABLE(boolean penetrable) { //setters of the penetration of the element
		PENETRABLE = penetrable;
	}

	public int getTYPE() { //getters of the type
		return TYPE;
	}

	public void setTYPE(int type) { //setters of the type
		TYPE = type;
	}
}

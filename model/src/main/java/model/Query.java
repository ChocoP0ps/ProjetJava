package model;

public class Query {
	
	public String getMapByLevel(int level){
		return "SELECT * FROM map WHERE LEVEL=" + level;
	}
	
}

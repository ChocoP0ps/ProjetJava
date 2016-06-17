package model;

public class Query {
	
	public String getMapByLevel(int level){
		return "SELECT * FROM map WHERE LEVEL=" + level;
	}
	
	public String addNameQuery(String name, int score){
		return "INSERT INTO `player`(`PSEUDO`, `SCORE`) VALUES ( \" " + name + "\"," + score + ")";
	}
	
	public String getNameByBestScore(){
		return "SELECT * FROM player ORDER BY SCORE DESC";
	}
}

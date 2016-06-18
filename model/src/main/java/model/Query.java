package model;

public class Query {
	
	public String getMapByLevel(int level){						//Query which get the map with the number of level 'level'
		return "SELECT * FROM map WHERE LEVEL=" + level;
	}
	
	public String addNameQuery(String name, int score){			//Query which add the name and the score to the table "player"
		return "INSERT INTO `player`(`PSEUDO`, `SCORE`) VALUES ( \" " + name + "\"," + score + ")";
	}
	
	public String getNameByBestScore(){							//Query which return the table "player" order by best score
		return "SELECT * FROM player ORDER BY SCORE DESC";
	}
}

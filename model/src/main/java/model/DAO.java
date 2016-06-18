package model;

import java.sql.*;

public class DAO {		//Class of connection with the BDD
	private static String URL = "jdbc:mysql://localhost:3306/projetjava?autoReconnect=true&useSSL=false";		//URL of the BDD
	private static String LOGIN = "SuperUtilisateur";			//Login to connect to the BDD
	private static String PASSWORD = "123456";					//Password to connect to the BDD
	private Connection connection;								//Object Connection
	private Statement statement;								//Object Statement
	private Query query;										//Object Query

	public DAO(){
		this.connection = null;		//Initiate the connection to null
		this.statement = null;		//Initiate the statement to null     
		query = new Query();		//Initiate the object Query
	}

	public Boolean open() {				//Method which open the connection with the BDD
		try {
			Class.forName("com.mysql.jdbc.Driver");		//Setting the driver for MySQL (JDBC)
			this.connection = DriverManager.getConnection(DAO.URL, DAO.LOGIN, DAO.PASSWORD);	//Connection to the BDD
			this.statement = this.connection.createStatement();									//Creation of the statement
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (final SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void close(){				//Method which close the connection to the BDD
		try {
			this.connection.close();	//Closing the connection
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getMap(int level){	//Get the map with the level
		try {
			ResultSet rs = this.statement.executeQuery(query.getMapByLevel(level));		//Get the resultset of the query
			if(rs.first()==true){						//Go to the first row
				return rs.getString("CARTE");			//return the column "CARTE"
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Failed";
	}
	
	public void addName(String name, int score){			//Saving a party (name + score)
		try {
			this.statement.executeUpdate(query.addNameQuery(name, score));		//Execute query
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getBestScore(int place){						//Get the score in the place 'place'
		try {
			ResultSet rs = this.statement.executeQuery(query.getNameByBestScore());		//Get the best score
			if(rs.first()==true){				//Come to the first row
				rs.absolute(place+1);			//Come to the row 'place' +1 (because place begin at 0)
			}
			return rs.getInt("SCORE");			//return the column "SCORE"
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public String getNameBestScore(int place){				//Get the name in the place 'place'
		try {
			ResultSet rs = this.statement.executeQuery(query.getNameByBestScore());		//Get the best score
			if(rs.first()==true){				//Come to the first row
				rs.absolute(place+1);			//Come to the row 'place' +1 (because place begin at 0)
			}
			return rs.getString("PSEUDO");		//return the column "PSEUDO"
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}

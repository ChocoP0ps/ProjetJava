package model;

import java.sql.*;

public class DAO {
	private static String URL = "jdbc:mysql://10.113.129.9:3306/projetjava?autoReconnect=true&useSSL=false";
	private static String LOGIN = "SuperUtilisateur";
	private static String PASSWORD = "123456";
	private Connection connection;
	private Statement statement;
	private Query query;

	public DAO(){
		this.connection = null;
		this.statement = null;      
		query = new Query();
	}

	public Boolean open() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(DAO.URL, DAO.LOGIN, DAO.PASSWORD);
			this.statement = this.connection.createStatement();
		} catch (final ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (final SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void close(){
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getMap(int level){
		try {
			ResultSet rs = this.statement.executeQuery(query.getMapByLevel(level));
			if(rs.first()==true){
				return rs.getString("CARTE");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Failed";
	}
	
	public void addName(String name, int score){
		try {
			this.statement.executeUpdate(query.addNameQuery(name, score));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getBestScore(int place){
		try {
			ResultSet rs = this.statement.executeQuery(query.getNameByBestScore());
			if(rs.first()==true){
				rs.absolute(place+1);
			}
			return rs.getInt("SCORE");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public String getNameBestScore(int place){
		try {
			ResultSet rs = this.statement.executeQuery(query.getNameByBestScore());
			if(rs.first()==true){
				rs.absolute(place+1);
			}
			return rs.getString("PSEUDO");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}

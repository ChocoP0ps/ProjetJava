package model;

import java.sql.*;

public class DAO {
	private static String URL = "jdbc:mysql://10.113.129.9:3306/projetjava?autoReconnect=true&useSSL=false";
	private static String LOGIN = "SuperUtilisateur";
	private static String PASSWORD = "123456";
	private Connection connection;
	@SuppressWarnings("unused")
	private Statement statement;

	public DAO(){
		this.connection = null;
		this.statement = null;
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
		String sql = "{call MapByLevel(?)}";
		CallableStatement call;
		try {
			call = connection.prepareCall(sql);
			call.setInt(1,level);
			if(call.execute()){ 
			    ResultSet resultat = call.getResultSet(); 
				if(resultat.first()==true){
					return resultat.getString("CARTE");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return "Failed";
	}
	
	public void addName(String name, int score){
		String sql = "{call InsertByScore(?, ?)}";
		CallableStatement call;
		try {
			call = connection.prepareCall(sql);
			call.setString(1,name);
			call.setInt(2, score);
			call.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public int getBestScore(int place){
		String sql = "{call GetBestScore()}";
		CallableStatement call;
		try {
			call = connection.prepareCall(sql);
			if(call.execute()){ 
			    ResultSet resultat = call.getResultSet(); 
				if(resultat.first()==true){
					resultat.absolute(place+1);
					return resultat.getInt("SCORE");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return 0;
	}
	
	public String getNameBestScore(int place){
		String sql = "{call GetBestScore()}";
		CallableStatement call;
		try {
			call = connection.prepareCall(sql);
			if(call.execute()){ 
			    ResultSet resultat = call.getResultSet(); 
				if(resultat.first()==true){
					resultat.absolute(place+1);
					return resultat.getString("PSEUDO");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
}

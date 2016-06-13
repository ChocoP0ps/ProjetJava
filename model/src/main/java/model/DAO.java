package model;

import java.sql.*;

public class DAO {
	private static String URL = "jdbc:mysql://10.113.129.15:3306/projetjava?autoReconnect=true&useSSL=false";
	private static String LOGIN = "root";
	private static String PASSWORD = "";
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
}

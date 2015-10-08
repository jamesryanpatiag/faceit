package com.group3.faceit.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDAO {
	private Connection connection = null;
	private String driver = "com.mysql.jdbc.Driver";
	private String protocol = "jdbc:mysql://";
	private String charSet = "?useUnicode=true&amp;characterEncoding=utf-8";
	private String server = "localhost";
	private String schema = "faceit";
	private int port = 3306;
	private String userName = "root";
	private String password = "root";
	
	String url = protocol + server + ":" + port + "/" + schema + charSet;
	
	protected Connection getConnection() {		
		try {
			Class.forName(driver);
			System.out.println("ssssssssssssssssssssss");
            connection = DriverManager.getConnection(url, userName, password);			
		} catch (SQLException e) {
			System.out.println("Unable to establish database connection: " + e);
		} catch (ClassNotFoundException e) {
			System.out.println("Library class does not exists: " + e);
		}
		return connection;
	}
	
	protected void release(Connection connection, ResultSet resultSet, PreparedStatement preparedStatement) {		
		try {
			if (connection != null) {
				connection.close();
			} else if (resultSet != null) {
				resultSet.close();
			} else if (preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			System.out.println("Unable to close database connection!");
		}		
	}
}

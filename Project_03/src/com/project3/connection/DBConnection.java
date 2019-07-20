package com.project3.connection;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnection {
	private static Connection con;
	static String databaseName = "project3";
	static String username = "root";
	static String password = "";
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/"+databaseName, username, password);
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}
	
}

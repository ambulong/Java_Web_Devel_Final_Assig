package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	private final static String driver = "com.mysql.jdbc.Driver" ;
	private final static String url = "jdbc:mysql://localhost:3306/test";
	private final static String user = "test";
	private final static String passwd = "test";
	
	public Connection getConnectDB() throws SQLException, Exception{
		Connection conn = null;
		
		Class.forName(driver);
		conn = DriverManager.getConnection(url, user, passwd);
		
		return conn;
	}
}

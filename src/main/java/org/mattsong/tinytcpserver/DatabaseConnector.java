package org.mattsong.tinytcpserver;

import java.sql.*;

public class DatabaseConnector {
  // mysql info
  static final String DRIVER = "com.mysql.cj.jdbc.Driver";
  static final String URL = "jdbc:mysql://localhost:3306/adventure";
  static final String USER = "root";
  static final String PASSWORD = "shylrl5638a";
  static {
		try {
	    Class.forName(DRIVER);
		} catch (Exception e) {
	    throw new RuntimeException("Error connecting to MySQL", e);
		}
	}

  static Connection getConnect() throws Exception {
		return DriverManager.getConnection(URL, USER, PASSWORD);
  }

}

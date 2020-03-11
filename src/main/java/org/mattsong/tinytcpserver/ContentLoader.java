package org.mattsong.tinytcpserver;

import java.sql.*;
import java.io.*;
import java.util.Scanner;

public class ContentLoader {
  private static final String UPDATE = "-";
  private static final String INSERT = "+";
    
  public static void main(String[] args) throws Exception {
		String token = args[0];
		String dir = args[1];
		int id = Integer.parseInt(args[2]);
	
		if (token.equals(UPDATE)) {
			update(dir, id);
		}
		else if (token.equals(INSERT)) {
	    insert(dir);
		}
		else throw new RuntimeException("Incorrect token");
  }

  private static void update(String dir, int id) throws Exception {	
		Scanner sc = new Scanner(new File(dir));
		String content_text = sc.nextLine();
	
		try (Connection connection = DatabaseConnector.getConnect()) {
	    try (Statement statement = connection.createStatement()) {
				String sql = "update content_tbl set content_text = '" + content_text + "' where content_id = " + id;
				statement.executeUpdate(sql);
	    }
		}
  }

  private static void insert(String dir) throws Exception {
		Scanner sc = new Scanner(new File(dir));
		String content_text = sc.nextLine();

		try (Connection connection = DatabaseConnector.getConnect()) {
	    try (Statement statement = connection.createStatement()) {
				String sql = "insert into content_tbl (content_text) values ('" + content_text + "')";
				statement.executeUpdate(sql);
	    }
		}
  }

}

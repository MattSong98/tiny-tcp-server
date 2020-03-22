package org.mattsong.tinytcpserver;

import java.sql.*;

public class User {
    
  private static final int maxN = 1000;
  /**
   ** UserID Service List
   */
  public static synchronized String signUp(String name, String password) {  
    String account = null;
    try (Connection connection = DatabaseConnector.getConnect()) {
      try (Statement statement = connection.createStatement()) {
        // get #(account)
        int N = maxN;
        String sql = "select count(*) from user_tbl";
        try (ResultSet result = statement.executeQuery(sql)) {
          while (result.next()) N = result.getInt(1);
        }

        // if new account availible
        if (N < maxN) {       
        // create new account
          sql = "insert into user_tbl (user_name, user_password) values ('" + name + "','" + password + "')";
          statement.executeUpdate(sql);

          // get new account' user_id
          sql = "select max(user_id) from user_tbl";
          try (ResultSet result = statement.executeQuery(sql)) {
            while (result.next()) account = result.getInt(1) + "";
          }
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return account;
  }

  public static boolean validation(String account, String password) {
    try (Connection connection = DatabaseConnector.getConnect()) {
      try (Statement statement = connection.createStatement()) {
        String user_password = null;
        String sql = "select user_password from user_tbl where user_id = " + account;
        try (ResultSet result = statement.executeQuery(sql)) {
          while (result.next()) {
            user_password = result.getString(1);
          }
        }
      
        if (user_password == null) return false;
        else if (!user_password.equals(password)) return false;
        else return true;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static void delete(String account) {
    try (Connection connection = DatabaseConnector.getConnect()) {
      try (Statement statement = connection.createStatement()) {
        String sql = "delete from user_tbl where user_id = " + account;
        statement.executeUpdate(sql);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
}

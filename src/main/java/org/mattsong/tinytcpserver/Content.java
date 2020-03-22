package org.mattsong.tinytcpserver;

import java.sql.*;

public class Content {
  static int contentNum;
  static {
    try (Connection connection = DatabaseConnector.getConnect()) {
      try (Statement statement = connection.createStatement()) {
        String sql = "select count(*) from content_tbl";
        try (ResultSet result = statement.executeQuery(sql)) {
          while (result.next()) contentNum = result.getInt(1);
        } 
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static String getContent() {
    String content_text = null;
    int N = getRandom(contentNum);
    try (Connection connection = DatabaseConnector.getConnect()) {
      try (Statement statement = connection.createStatement()) {
        String sql = "select content_text from content_tbl where content_id = " + N;
        try (ResultSet result = statement.executeQuery(sql)) {
          while (result.next()) content_text = result.getString(1);
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return content_text;
  }

  private static int getRandom(final int bound) {
    return (int)(Math.random() * bound);
  }
}

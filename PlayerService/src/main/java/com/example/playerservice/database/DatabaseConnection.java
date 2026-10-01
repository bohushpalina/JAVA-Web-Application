package com.example.playerservice.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
  private static String URL = "jdbc:sqlite:src/main/resources/database.db";

  public static Connection getConnection() {
    try {
      Connection connection = DriverManager.getConnection(URL);
      System.out.println("Base is connected");
      return connection;
    }
    catch(SQLException e) {
      System.out.println(e.toString());
      throw new RuntimeException(e);
    }
  }
}

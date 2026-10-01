package com.example.playerservice.database.repository;

import com.example.playerservice.database.model.Player;
import com.example.playerservice.database.model.PlayerHistory;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.example.playerservice.database.DatabaseConnection.getConnection;

public class PlayerRepository {

  public static List<Player> findAll(){

    try {
      Connection connection = getConnection();
      Statement statement = connection.createStatement();
      ResultSet result = statement.executeQuery("SELECT * FROM Roster");
      List<Player> list = new ArrayList();
      while (result.next())
      {
        Player player = new Player(result.getString("playerid"), result.getInt("jersey"), result.getString("fname"), result.getString("sname"), result.getString("position"), LocalDateTime.parse(result.getString("birthday"), DateTimeFormatter.ofPattern("MM/dd/yy HH:mm:ss")), result.getInt("weight"), result.getInt("height"), result.getString("birthcity"), result.getString("birthstate"));
        list.add(player);
      }
      return(list);
    }
    catch(SQLException e){
      System.out.println(e.toString());
      throw new RuntimeException(e);
    }
  }

  public static Player findPlayer(String playerId){
    try{
      Connection connection = getConnection();
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM Roster WHERE playerid = ?");
      statement.setString(1, playerId);
      ResultSet result = statement.executeQuery();
      result.next();
      Player player = new Player(result.getString("playerid"), result.getInt("jersey"), result.getString("fname"), result.getString("sname"), result.getString("position"), LocalDateTime.parse(result.getString("birthday"), DateTimeFormatter.ofPattern("MM/dd/yy HH:mm:ss")), result.getInt("weight"), result.getInt("height"), result.getString("birthcity"), result.getString("birthstate"));
      return player;
    }
    catch(SQLException e) {
      System.out.print(e.toString());
      throw new RuntimeException(e);
    }
  }


  public static List<String> getUniquePositions(){

    try {
      Connection connection = getConnection();
      Statement statement = connection.createStatement();
      ResultSet result = statement.executeQuery("SELECT DISTINCT position FROM roster;");
      List<String> list = new ArrayList();
      while (result.next())
      {
        list.add(result.getString("position"));
      }
      return(list);
    }
    catch(SQLException e){
      System.out.println(e.toString());
      throw new RuntimeException(e);
    }
  }

  public static List<Player> findByFilters(String position,
                                           Integer birthYearFrom,
                                           Integer birthYearTo,
                                           Integer weightFrom,
                                           Integer weightTo,
                                           Integer heightFrom,
                                           Integer heightTo) {
    try {
      Connection connection = getConnection();
      List<Object> params = new ArrayList<>();
      String sql = "SELECT * FROM Roster WHERE 1=1";
      if(position != null && !position.isEmpty())
      {
        sql += " AND position = ?";
        params.add(position);
      }
      if (birthYearFrom != null) {
        sql += " AND birthyear >= ?";
        params.add(birthYearFrom);
      }
      if (birthYearTo != null) {
        sql += " AND birthyear <= ?";
        params.add(birthYearTo);
      }
      if(weightFrom != null)
      {
        sql += " AND weight >= ?";
        params.add(weightFrom);
      }
      if(weightTo != null)
      {
        sql += " AND weight <= ?";
        params.add(weightTo);
      }
      if(heightFrom != null)
      {
        sql += " AND height >= ?";
        params.add(heightFrom);
      }
      if(heightTo != null)
      {
        sql += " AND height <= ?";
        params.add(heightTo);
      }
      PreparedStatement statement = connection.prepareStatement(sql);
      for (int i = 0; i < params.size(); ++i)
      {
        statement.setObject(i+1, params.get(i));
      }
      ResultSet result = statement.executeQuery();
      List<Player> list = new ArrayList();
      while (result.next())
      {
        Player player = new Player(result.getString("playerid"), result.getInt("jersey"), result.getString("fname"), result.getString("sname"), result.getString("position"), LocalDateTime.parse(result.getString("birthday"), DateTimeFormatter.ofPattern("MM/dd/yy HH:mm:ss")), result.getInt("weight"), result.getInt("height"), result.getString("birthcity"), result.getString("birthstate"));
        list.add(player);
      }
      return(list);
    }
    catch(SQLException e){
      System.out.println(e.toString());
      throw new RuntimeException(e);
    }
  }

  public static List<PlayerHistory> getInfo(Player player)
  {
      return PlayerHistoryRepository.findByPlayerId(player.getPlayerid());
  }

  public static String getPhoto(Player player)
  {
    try{
      Connection connection = getConnection();
      PreparedStatement statement = connection.prepareStatement("SELECT photo FROM Roster WHERE playerid = ?");
      statement.setString(1, player.getPlayerid());
      ResultSet result = statement.executeQuery();
      if(result.next())
      {
        return result.getString("photo");
      }
      return null;
    }
    catch(SQLException e) {
      System.out.print(e.toString());
      throw new RuntimeException(e);
    }
  }
}

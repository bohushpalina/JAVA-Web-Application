package com.example.playerservice.database.repository;

import com.example.playerservice.database.model.PlayerHistory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.playerservice.database.DatabaseConnection.getConnection;

public class PlayerHistoryRepository {
  public static List<PlayerHistory> findByPlayerId(String playerid){
    try{
      Connection connection = getConnection();
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM PlayerHistory WHERE playerid = ?");
      statement.setString(1, playerid);
      ResultSet result = statement.executeQuery();
      List<PlayerHistory> playerHistories= new ArrayList();
      while(result.next())
      {
        PlayerHistory plh = new PlayerHistory(
                result.getString("playerid"),
                result.getString("season"),
                result.getString("team")
        );
        playerHistories.add(plh);
      }
      return playerHistories;
    }
    catch(SQLException e){
      System.out.println(e.toString());
      throw new RuntimeException(e);
    }
  }
}

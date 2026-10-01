package com.example.lab1.database.model;

public class PlayerHistory {
  private String playerid;
  private String season;
  private String team;

  public PlayerHistory(String playerid, String season, String team) {
    this.playerid = playerid;
    this.season = season;
    this.team = team;
  }

  public String getPlayerid() {
    return playerid;
  }

  public void setPlayerid(String playerid) {
    this.playerid = playerid;
  }

  public String getSeason() {
    return season;
  }

  public void setSeason(String season) {
    this.season = season;
  }

  public String getTeam() {
    return team;
  }

  public void setTeam(String team) {
    this.team = team;
  }
}

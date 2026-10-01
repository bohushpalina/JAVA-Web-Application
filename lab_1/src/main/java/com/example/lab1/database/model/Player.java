package com.example.lab1.database.model;
import java.time.LocalDateTime;

public class Player {
  private String playerid;
  private int jersey;
  private String fname;
  private String sname;
  private String position;
  private LocalDateTime birthday;
  private int weight;
  private int height;
  private String birthcity;
  private String birthstate;
  private int birthyear;

  public Player(String playerid, int jersey, String fname, String sname, String position, LocalDateTime birthday, int weight, int height, String birthcity, String birthstate) {
    this.playerid = playerid;
    this.jersey = jersey;
    this.fname = fname;
    this.sname = sname;
    this.position = position;
    this.birthday = birthday;
    this.weight = weight;
    this.height = height;
    this.birthcity = birthcity;
    this.birthstate = birthstate;
  }

  public String getPlayerid() {
    return playerid;
  }

  public int getBirthyear() {
    return birthyear;
  }

  public void setBirthyear(int birthyear) {
    this.birthyear = birthyear;
  }

  public void setPlayerid(String playerid) {
    this.playerid = playerid;
  }

  public int getJersey() {
    return jersey;
  }

  public void setJersey(int jersey) {
    this.jersey = jersey;
  }

  public String getFname() {
    return fname;
  }

  public void setFname(String fname) {
    this.fname = fname;
  }

  public String getSname() {
    return sname;
  }

  public void setSname(String sname) {
    this.sname = sname;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public LocalDateTime getBirthday() {
    return birthday;
  }

  public void setBirthday(LocalDateTime birthday) {
    this.birthday = birthday;
  }

  public int getWeight() {
    return weight;
  }

  public void setWeight(int weight) {
    this.weight = weight;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public String getBirthcity() {
    return birthcity;
  }

  public void setBirthcity(String birthcity) {
    this.birthcity = birthcity;
  }

  public String getBirthstate() {
    return birthstate;
  }

  public void setBirthstate(String birthstate) {
    this.birthstate = birthstate;
  }

  public Player(String playerid) {
    this.playerid = playerid;
  }
}

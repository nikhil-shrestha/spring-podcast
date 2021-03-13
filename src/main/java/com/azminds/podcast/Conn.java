package com.azminds.podcast;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conn {
  public static void main(String[] args) {
    Connection connection = null;
    String host="localhost";
    String port="5432";
    String db_name="podcast_demo";
    String username="illusionist";
    String password="illusion";
    try {
      Class.forName("org.postgresql.Driver");
      connection = DriverManager.getConnection("jdbc:postgresql://"+host+":"+port+"/"+db_name+"", ""+username+"", ""+password+"");
      if (connection != null) {
        System.out.println("Connection OK");
      } else {
        System.out.println("Connection Failed");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

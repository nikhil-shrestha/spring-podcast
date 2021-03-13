package com.azminds.podcast.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBConnection {

  private static final Logger log = LoggerFactory.getLogger(DBConnection.class);
  private Connection conn = null;

  public void close() {
    if (conn != null) {
      try {
        log.info("Closing database connection to sampleDB");
        conn.close();
      } catch (SQLException e) {
        log.error("Unable to close connection", e);
      }
      conn = null;
    }
  }

  public Connection getConnection() throws SQLException {
    String host="localhost";
    String port="5432";
    String db_name="podcast_demo";
    String username="illusionist";
    String password="illusion";

    if (conn == null) {
      log.info("Opening connection to sampleDB");
      conn = DriverManager.getConnection("jdbc:postgresql://"+host+":"+port+"/"+db_name+"", ""+username+"", ""+password+"");
    }
    return conn;
  }
}

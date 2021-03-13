package com.azminds.podcast.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.azminds.podcast.entity.PodcastEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PodcastDAO {
  private static final Logger log = LoggerFactory.getLogger(PodcastDAO.class);

  private Connection conn;

  /**
   * Create a table in the database
   */
  public boolean createTable() {
    boolean success = false;
    if (conn != null) {
      Statement stmt = null;

      try {
        stmt = conn.createStatement();
        stmt.execute("create table podcast (" +
            "        id serial primary key," +
            "        artist_name varchar(255)," +
            "        artist_view_url varchar(255)," +
            "        artwork_url_100 varchar(255)," +
            "        artwork_url_30 varchar(255)," +
            "        artwork_url_512 varchar(255)," +
            "        artwork_url_60 varchar(255)," +
            "        artwork_url_600 varchar(255)," +
            "        collection_id int8 not null," +
            "        collection_name varchar(255) not null," +
            "        collection_view_url varchar(255)," +
            "        copyright varchar(255)," +
            "        country varchar(255)," +
            "        current_version_release_date varchar(255)," +
            "        description varchar," +
            "        episode_count int4," +
            "        feed_url varchar(255)," +
            "        kind varchar(255)," +
            "        long_description varchar," +
            "        preview_url varchar(255)," +
            "        release_date varchar(255)," +
            "        short_description varchar(255)," +
            "        track_count int4," +
            "        wrapper_type varchar(255))");
        log.info("Creating podcast_table");
        success = true;
      } catch (SQLException e) {
        log.error("Unable to create the database table", e);
      } finally {
        if (stmt != null) {
          try {
            stmt.close();
          } catch (SQLException e) {
          }
        }
      }
    }
    return success;
  }

  /**
   * Remove our podcast table
   *
   * @return
   */
  public boolean dropTable() {
    boolean success = false;
    if (conn != null) {
      Statement stmt = null;

      try {
        stmt = conn.createStatement();
        stmt.execute("DROP TABLE podcast");
        log.info("Deleting podcast");
        success = true;
      } catch (SQLException e) {
        log.error("Unable to create the database table", e);
      } finally {
        if (stmt != null) {
          try {
            stmt.close();
          } catch (SQLException e) {
          }
        }
      }
    }
    return success;
  }

  /**
   * Insert a podcast into the database
   *
   */
  public boolean insertPodcast(PodcastEntity podcast) {
    boolean success = false;

    if (podcast != null) {
      if (conn != null) {
        PreparedStatement pstmt = null;

        try {
          pstmt = conn.prepareStatement("INSERT INTO podcast (" +
              "artist_name, artist_view_url, artwork_url_100, artwork_url_30, artwork_url_512, " +
              "artwork_url_60, artwork_url_600, collection_id, collection_name, collection_view_url, copyright, " +
              "country, current_version_release_date, description, episode_count, feed_url, kind, long_description, " +
              "preview_url, release_date, short_description, track_count, wrapper_type) " +
              "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
          pstmt.setString(1, podcast.getArtistName());
          pstmt.setString(2, podcast.getArtistViewUrl());
          pstmt.setString(3, podcast.getArtworkUrl100());
          pstmt.setString(4, podcast.getArtworkUrl30());
          pstmt.setString(5, podcast.getArtworkUrl512());
          pstmt.setString(6, podcast.getArtworkUrl60());
          pstmt.setString(7, podcast.getArtworkUrl600());
          pstmt.setLong(8, podcast.getCollectionId());
          pstmt.setString(9, podcast.getCollectionName());
          pstmt.setString(10, podcast.getCollectionViewUrl());
          pstmt.setString(11, podcast.getCopyright());
          pstmt.setString(12, podcast.getCountry());
          pstmt.setString(13, podcast.getCurrentVersionReleaseDate());
          pstmt.setString(14, podcast.getDescription());
          pstmt.setInt(15, podcast.getEpisodeCount());
          pstmt.setString(16, podcast.getFeedUrl());
          pstmt.setString(17, podcast.getKind());
          pstmt.setString(18, podcast.getLongDescription());
          pstmt.setString(19, podcast.getPreviewUrl());
          pstmt.setString(20, podcast.getReleaseDate());
          pstmt.setString(21, podcast.getShortDescription());
          pstmt.setInt(22, podcast.getTrackCount());
          pstmt.setString(23, podcast.getWrapperType());

          success = (pstmt.executeUpdate() == 1);  // success means exactly one row inserted
        } catch (SQLException e) {
          log.error("Unable to insert" + podcast.getCollectionName() + " " + podcast.getCollectionId() + "into the table", e);
        } finally {
          if (pstmt != null) {
            try {
              pstmt.close();
            } catch (SQLException e) {
            }
          }
        }
      }
    }
    return success;
  }

  /**
   * Get all the podcast stored in the database
   *
   * @return
   */
  public List<PodcastEntity> getAllPodcast() {
    log.debug("getAllPodcast()");
    List<PodcastEntity> podcasts = new ArrayList<PodcastEntity>();
    PodcastEntity podcast;
    if (conn != null) {
      Statement stmt = null;

      try {
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM podcast");

        while (rs.next()) {
          podcast = new PodcastEntity();
          podcast.setId(rs.getLong("id"));
          podcast.setCollectionId(rs.getLong("collection_id"));
          podcast.setCollectionName(rs.getString("collection_name"));
          podcasts.add(podcast);
        }
      } catch (SQLException e) {
        log.error("Unable to create the database table", e);
      } finally {
        if (stmt != null) {
          try {
            stmt.close();
          } catch (SQLException e) {
          }
        }
      }
    }

    return podcasts;
  }

  /**
   * Get a podcast by their unique ID
   *
   * @param id
   * @return
   */
  public PodcastEntity getPodcastById(int id) {
    log.debug("getPersonById({})", id);
    PodcastEntity podcast = null;
    if (conn != null) {
      PreparedStatement pstmt = null;

      try {
        pstmt = conn.prepareStatement("SELECT * FROM podcast WHERE id = ?");
        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
          podcast = new PodcastEntity();
          podcast.setId(rs.getLong("id"));
          podcast.setCollectionName(rs.getString("collection_name"));
          podcast.setCollectionId(rs.getLong("collection_id"));
        } else {
          log.warn("No person found for id = {}", id);
        }
      } catch (SQLException e) {
        log.error("Unable query person by ID(" + id + ")", e);
      } finally {
        if (pstmt != null) {
          try {
            pstmt.close();
          } catch (SQLException e) {
          }
        }
      }
    }

    return podcast;
  }

  /**
   * Assign the connection to use for this DAO
   *
   * @param conn
   */
  public void setConn(Connection conn) {
    this.conn = conn;
  }
}

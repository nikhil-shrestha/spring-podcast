package com.azminds.podcast.dao;

import com.azminds.podcast.entity.EpisodeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EpisodeDao {
  private static final Logger log = LoggerFactory.getLogger(EpisodeDao.class);

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
        stmt.execute("create table episode (" +
            "        id serial primary key," +
            "        description varchar," +
            "        duration varchar(255)," +
            "        episode_number varchar(255)," +
            "        guid varchar(255) not null," +
            "        link varchar(255) not null," +
            "        pub_date DATE not null," +
            "        title varchar(255) not null," +
            "        podcast_id int8 not null)");
        log.info("Creating episode_table");
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
        stmt.execute("DROP TABLE episode");
        log.info("Deleting episode");
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
  public boolean insertEpisode(EpisodeEntity episode) {
    boolean success = false;

    System.out.println(episode);

    if (episode != null) {
      if (conn != null) {
        PreparedStatement pstmt = null;

        try {
          pstmt = conn.prepareStatement("INSERT INTO episode (" +
              "description, duration, guid, link, pub_date, title, podcast_id)" +
              "VALUES (?, ?, ?, ?, ?, ?, ?)");
          pstmt.setString(1, episode.getDescription());
          pstmt.setString(2, episode.getDuration());
//          pstmt.setString(3, episode.getEpisodeNumber());
          pstmt.setString(3, episode.getGuid());
          pstmt.setString(4, episode.getLink());
          pstmt.setDate(5, new Date(episode.getPubDate().getTime()));
          pstmt.setString(6, episode.getTitle());
          pstmt.setLong(7, episode.getPodcastId());

          success = (pstmt.executeUpdate() == 1);  // success means exactly one row inserted
        } catch (SQLException e) {
          log.error("Unable to insert" + episode.getGuid() + " " + episode.getTitle() + "into the table", e);
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
  public List<EpisodeEntity> getAllEpisode() {
    log.debug("getAllPodcast()");
    List<EpisodeEntity> episodes = new ArrayList<EpisodeEntity>();
    EpisodeEntity episode;
    if (conn != null) {
      Statement stmt = null;

      try {
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM episode");

        while (rs.next()) {
          episode = new EpisodeEntity();
          episode.setId(rs.getLong("id"));
          episode.setTitle(rs.getString("title"));
          episode.setDescription(rs.getString("description"));
          episodes.add(episode);
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

    return episodes;
  }

  /**
   * Get a podcast by their unique ID
   *
   * @param id
   * @return
   */
  public EpisodeEntity getEpisodeById(int id) {
    log.debug("getPersonById({})", id);
    EpisodeEntity episode = null;
    if (conn != null) {
      PreparedStatement pstmt = null;

      try {
        pstmt = conn.prepareStatement("SELECT * FROM episode WHERE id = ?");
        pstmt.setInt(1, id);

        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
          episode = new EpisodeEntity();
          episode.setId(rs.getLong("id"));
          episode.setTitle(rs.getString("title"));
          episode.setDescription(rs.getString("description"));
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

    return episode;
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

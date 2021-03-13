package com.azminds.podcast;

import be.ceau.itunesapi.Lookup;
import be.ceau.itunesapi.request.Entity;
import be.ceau.itunesapi.response.Response;
import be.ceau.itunesapi.response.Result;
import com.azminds.podcast.dao.EpisodeDao;
import com.azminds.podcast.dao.PodcastDAO;
import com.azminds.podcast.entity.EpisodeEntity;
import com.azminds.podcast.entity.PodcastEntity;
import com.azminds.podcast.helper.DBConnection;
import com.icosillion.podengine.models.Episode;
import com.icosillion.podengine.models.Podcast;
import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Read newly generated CSV
 */
public class PodcastParser {

  private static final Logger log = LoggerFactory.getLogger(PodcastParser.class);

  public static ArrayList<PodcastCSV> readCsv(String SAMPLE_CSV_FILE_PATH) {
    ArrayList<PodcastCSV> podcasts = new ArrayList<>();
    try (
        Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH))
    ) {
      CSVReader csvReader = new CSVReader(reader);

      // Reading Records One by One in a String array
      String[] nextRecord;
      while ((nextRecord = csvReader.readNext()) != null) {
        PodcastCSV podcast = new PodcastCSV(nextRecord[0], nextRecord[1], nextRecord[2], nextRecord[3], nextRecord[4], nextRecord[5], nextRecord[6]);
        podcasts.add(podcast);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return podcasts;
  }

  public static void main(String[] args) throws SQLException {

    DBConnection dbCon = new DBConnection();
    PodcastDAO podcastDAO = new PodcastDAO();
    podcastDAO.setConn(dbCon.getConnection());
    podcastDAO.createTable();
    EpisodeDao episodeDao = new EpisodeDao();
    episodeDao.setConn(dbCon.getConnection());
    episodeDao.createTable();

    ArrayList<PodcastCSV> records = readCsv("FileNumber_1.csv");
    Partition<PodcastCSV> arrayChunk = Partition.ofSize(records, 25);
    arrayChunk.forEach(arr -> {
      Collection<String> arrIds = arr.stream().map(item -> item.getItunesId()).collect(Collectors.toList());
      Response response = new Lookup()
          .setIds(arrIds)
          .setEntity(Entity.PODCAST)
          .execute();

      Collection<Result> results = response.getResults();
      results.forEach(rslt -> {
        System.out.println(rslt);
        PodcastEntity podcastEntity = new PodcastEntity();
        podcastEntity.setWrapperType(rslt.getWrapperType());
        podcastEntity.setKind(rslt.getKind());
        podcastEntity.setArtistId(rslt.getArtistId());
        podcastEntity.setCollectionId(rslt.getCollectionId());
        podcastEntity.setArtistName(rslt.getArtistName());
        podcastEntity.setCollectionName(rslt.getCollectionName());
        podcastEntity.setArtistViewUrl(rslt.getArtistViewUrl());
        podcastEntity.setCollectionViewUrl(rslt.getCollectionViewUrl());
        podcastEntity.setFeedUrl(rslt.getFeedUrl());
        podcastEntity.setPreviewUrl(rslt.getPreviewUrl());
        podcastEntity.setArtworkUrl30(rslt.getArtworkUrl30());
        podcastEntity.setArtworkUrl60(rslt.getArtworkUrl60());
        podcastEntity.setArtworkUrl100(rslt.getArtworkUrl100());
        podcastEntity.setArtworkUrl512(rslt.getArtworkUrl512());
        podcastEntity.setArtworkUrl600(rslt.getArtworkUrl600());
        podcastEntity.setReleaseDate(rslt.getReleaseDate());
        podcastEntity.setCountry(rslt.getCountry());
        podcastEntity.setShortDescription(rslt.getShortDescription());
        podcastEntity.setLongDescription(rslt.getLongDescription());
        podcastEntity.setDescription(rslt.getDescription());
        podcastEntity.setTrackCount(rslt.getTrackCount());
        podcastEntity.setCopyright(rslt.getCopyright());

        try {
          // Download and parse the Cortex RSS feed
//          Podcast podcast = new Podcast(new URL("https://www.relay.fm/cortex/feed"));
          Podcast podcastData = new Podcast(new URL(rslt.getFeedUrl()));
          System.out.println("- " + podcastData.getTitle() + " " + podcastData.getEpisodes().size());
          podcastEntity.setDescription(podcastData.getDescription());
          podcastEntity.setEpisodeCount(podcastData.getEpisodes().size());

          Collection<Episode> episodes = podcastData.getEpisodes();
          // List all episodes
          for (Episode episode : episodes) {
            System.out.println("- " + episode.getGUID() + " " + episode.getTitle() + " [" + episode.getPubDate().toString() + "]");
            System.out.println("--- " + episode.getITunesInfo().getDuration() + " " + episode.getITunesInfo().getEpisodeNumber());
            System.out.println("\n");
            EpisodeEntity episodeEntity = new EpisodeEntity();
            episodeEntity.setTitle(episode.getTitle());
            episodeEntity.setDescription(episode.getDescription());
            episodeEntity.setGuid(episode.getGUID());
            episodeEntity.setLink(episode.getLink());
            episodeEntity.setPubDate(episode.getPubDate());
//            episodeEntity.setEpisodeNumber(episode.getITunesInfo().getEpisodeNumber());
            episodeEntity.setDuration(episode.getITunesInfo().getDuration());
            episodeEntity.setPodcastId(rslt.getCollectionId());
            episodeDao.insertEpisode(episodeEntity);

          }
        } catch (Exception e) {
          System.out.println("Exception::");
          System.out.println(e);
        }


        podcastDAO.insertPodcast(podcastEntity);
      });
    });
    //    }

        /*Response response = new Lookup()
                .addId("1322200189")
                .setEntity(Entity.PODCAST)
                .execute();

        Collection<Result> results = response.getResults();
        results.forEach(rslt -> {
            System.out.println(rslt);
            try {
                //Download and parse the Cortex RSS feed
//                Podcast podcast = new Podcast(new URL("https://www.relay.fm/cortex/feed"));
                Podcast podcast = new Podcast(new URL(rslt.getFeedUrl()));

                //Display Feed Details
                System.out.printf("💼 %s has %d episodes!\n", podcast.getTitle(), podcast.getEpisodes().size());

                Collection<Episode> episodes = podcast.getEpisodes();
                //List all episodes
                for (Episode episode : episodes) {
                    System.out.println("- " + episode.getGUID() + " " + episode.getTitle() + " [" + episode.getPubDate().toString() + "]");
                    System.out.println("--- " + episode.getITunesInfo().getDuration());
                    System.out.println("\n");
                }
            } catch (Exception e) {
                System.out.println(e);
            }

        });*/
  }
}

package com.azminds.podcast.entity;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;

public class EpisodeEntity {
  private Long id;
  private LocalDateTime createdAt;
  private String title;
  private String description;
  private String guid;
  private String link;
  private String sourceName;
  private String sourceLink;
  private Date pubDate;
  private String episodeNumber;
  private String duration;
  private Long podcastId;

  public EpisodeEntity(String title,
                       String description,
                       String guid,
                       String link,
                       String sourceName,
                       String sourceLink,
                       Date pubDate,
                       String episodeNumber,
                       String duration,
                       Long podcastId) {
    this.title = title;
    this.description = description;
    this.guid = guid;
    this.link = link;
    this.sourceName = sourceName;
    this.sourceLink = sourceLink;
    this.pubDate = pubDate;
    this.episodeNumber = episodeNumber;
    this.duration = duration;
    this.podcastId = podcastId;
  }

  public EpisodeEntity() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getGuid() {
    return guid;
  }

  public void setGuid(String guid) {
    this.guid = guid;
  }

  public String getLink() {
    return link;
  }

  public void setLink(URL link) {
    this.link = link.toString();
  }

  public String getSourceName() {
    return sourceName;
  }

  public void setSourceName(String sourceName) {
    this.sourceName = sourceName;
  }

  public String getSourceLink() {
    return sourceLink;
  }

  public void setSourceLink(URL sourceLink) {
    this.sourceLink = sourceLink.toString();
  }

  public Date getPubDate() {
    return pubDate;
  }

  public void setPubDate(Date pubDate) {
    this.pubDate = pubDate;
  }

  public String getEpisodeNumber() {
    return episodeNumber;
  }

  public void setEpisodeNumber(Integer episodeNumber) {
    this.episodeNumber = episodeNumber.toString();
  }

  public String getDuration() {
    return duration;
  }

  public void setDuration(String duration) {
    this.duration = duration;
  }

  public Long getPodcastId() {
    return podcastId;
  }

  public void setPodcastId(Long podcastId) {
    this.podcastId = podcastId;
  }
}

package com.azminds.podcast.entity;

public class PodcastEntity {
  private Long id;
  private String wrapperType;
  private String kind;
  private Long artistId;
  private Long collectionId;
  private String artistName;
  private String collectionName;
  private String artistViewUrl;
  private String collectionViewUrl;
  private String feedUrl;
  private String previewUrl;
  private String artworkUrl30;
  private String artworkUrl60;
  private String artworkUrl100;
  private String artworkUrl512;
  private String artworkUrl600;
  private String releaseDate;
  private Integer trackCount;
  private String copyright;
  private String country;
  private String shortDescription;
  private String longDescription;
  private String description;
  private String currentVersionReleaseDate;
  private Integer episodeCount;

  public PodcastEntity(Long id,
                       String wrapperType,
                       String kind,
                       Long artistId,
                       Long collectionId,
                       String artistName,
                       String collectionName,
                       String artistViewUrl,
                       String collectionViewUrl,
                       String feedUrl,
                       String previewUrl,
                       String artworkUrl30,
                       String artworkUrl60,
                       String artworkUrl100,
                       String artworkUrl512,
                       String artworkUrl600,
                       String releaseDate,
                       Integer trackCount,
                       String copyright,
                       String country,
                       String shortDescription,
                       String longDescription,
                       String description,
                       String currentVersionReleaseDate) {
    this.id = id;
    this.wrapperType = wrapperType;
    this.kind = kind;
    this.artistId = artistId;
    this.collectionId = collectionId;
    this.artistName = artistName;
    this.collectionName = collectionName;
    this.artistViewUrl = artistViewUrl;
    this.collectionViewUrl = collectionViewUrl;
    this.feedUrl = feedUrl;
    this.previewUrl = previewUrl;
    this.artworkUrl30 = artworkUrl30;
    this.artworkUrl60 = artworkUrl60;
    this.artworkUrl100 = artworkUrl100;
    this.artworkUrl512 = artworkUrl512;
    this.artworkUrl600 = artworkUrl600;
    this.releaseDate = releaseDate;
    this.trackCount = trackCount;
    this.copyright = copyright;
    this.country = country;
    this.shortDescription = shortDescription;
    this.longDescription = longDescription;
    this.description = description;
    this.currentVersionReleaseDate = currentVersionReleaseDate;
  }

  public PodcastEntity() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getWrapperType() {
    return wrapperType;
  }

  public void setWrapperType(String wrapperType) {
    this.wrapperType = wrapperType;
  }

  public String getKind() {
    return kind;
  }

  public void setKind(String kind) {
    this.kind = kind;
  }

  public Long getArtistId() {
    return artistId;
  }

  public void setArtistId(Long artistId) {
    this.artistId = artistId;
  }

  public Long getCollectionId() {
    return collectionId;
  }

  public void setCollectionId(Long collectionId) {
    this.collectionId = collectionId;
  }

  public String getArtistName() {
    return artistName;
  }

  public void setArtistName(String artistName) {
    this.artistName = artistName;
  }

  public String getCollectionName() {
    return collectionName;
  }

  public void setCollectionName(String collectionName) {
    this.collectionName = collectionName;
  }

  public String getArtistViewUrl() {
    return artistViewUrl;
  }

  public void setArtistViewUrl(String artistViewUrl) {
    this.artistViewUrl = artistViewUrl;
  }

  public String getCollectionViewUrl() {
    return collectionViewUrl;
  }

  public void setCollectionViewUrl(String collectionViewUrl) {
    this.collectionViewUrl = collectionViewUrl;
  }

  public String getFeedUrl() {
    return feedUrl;
  }

  public void setFeedUrl(String feedUrl) {
    this.feedUrl = feedUrl;
  }

  public String getPreviewUrl() {
    return previewUrl;
  }

  public void setPreviewUrl(String previewUrl) {
    this.previewUrl = previewUrl;
  }

  public String getArtworkUrl30() {
    return artworkUrl30;
  }

  public void setArtworkUrl30(String artworkUrl30) {
    this.artworkUrl30 = artworkUrl30;
  }

  public String getArtworkUrl60() {
    return artworkUrl60;
  }

  public void setArtworkUrl60(String artworkUrl60) {
    this.artworkUrl60 = artworkUrl60;
  }

  public String getArtworkUrl100() {
    return artworkUrl100;
  }

  public void setArtworkUrl100(String artworkUrl100) {
    this.artworkUrl100 = artworkUrl100;
  }

  public String getArtworkUrl512() {
    return artworkUrl512;
  }

  public void setArtworkUrl512(String artworkUrl512) {
    this.artworkUrl512 = artworkUrl512;
  }

  public String getArtworkUrl600() {
    return artworkUrl600;
  }

  public void setArtworkUrl600(String artworkUrl600) {
    this.artworkUrl600 = artworkUrl600;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }

  public Integer getTrackCount() {
    return trackCount;
  }

  public void setTrackCount(Integer trackCount) {
    this.trackCount = trackCount;
  }

  public String getCopyright() {
    return copyright;
  }

  public void setCopyright(String copyright) {
    this.copyright = copyright;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getShortDescription() {
    return shortDescription;
  }

  public void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }

  public String getLongDescription() {
    return longDescription;
  }

  public void setLongDescription(String longDescription) {
    this.longDescription = longDescription;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCurrentVersionReleaseDate() {
    return currentVersionReleaseDate;
  }

  public void setCurrentVersionReleaseDate(String currentVersionReleaseDate) {
    this.currentVersionReleaseDate = currentVersionReleaseDate;
  }

  public Integer getEpisodeCount() {
    return episodeCount;
  }

  public void setEpisodeCount(Integer episodeCount) {
    this.episodeCount = episodeCount;
  }

  @Override
  public String toString() {
    return "PodcastEntity{" +
        "id=" + id +
        ", wrapperType='" + wrapperType + '\'' +
        ", kind='" + kind + '\'' +
        ", artistId=" + artistId +
        ", collectionId=" + collectionId +
        ", artistName='" + artistName + '\'' +
        ", collectionName='" + collectionName + '\'' +
        ", artistViewUrl='" + artistViewUrl + '\'' +
        ", collectionViewUrl='" + collectionViewUrl + '\'' +
        ", feedUrl='" + feedUrl + '\'' +
        ", previewUrl='" + previewUrl + '\'' +
        ", artworkUrl30='" + artworkUrl30 + '\'' +
        ", artworkUrl60='" + artworkUrl60 + '\'' +
        ", artworkUrl100='" + artworkUrl100 + '\'' +
        ", artworkUrl512='" + artworkUrl512 + '\'' +
        ", artworkUrl600='" + artworkUrl600 + '\'' +
        ", releaseDate='" + releaseDate + '\'' +
        ", trackCount=" + trackCount +
        ", copyright='" + copyright + '\'' +
        ", country='" + country + '\'' +
        ", shortDescription='" + shortDescription + '\'' +
        ", longDescription='" + longDescription + '\'' +
        ", description='" + description + '\'' +
        ", currentVersionReleaseDate='" + currentVersionReleaseDate + '\'' +
        ", episodeCount=" + episodeCount +
        '}';
  }
}

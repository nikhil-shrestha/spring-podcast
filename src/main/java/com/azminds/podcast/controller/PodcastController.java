package com.azminds.podcast.controller;

import com.azminds.podcast.exception.ResourceNotFoundException;
import com.azminds.podcast.model.Episode;
import com.azminds.podcast.model.Podcast;
import com.azminds.podcast.repository.EpisodeRepository;
import com.azminds.podcast.repository.PodcastRepository;
import com.azminds.podcast.service.PodcastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/podcast")
public class PodcastController {

  private static final Logger logger = LoggerFactory.getLogger(PodcastController.class);

  @Autowired
  private PodcastService service;
  @Autowired
  private PodcastRepository podcastRepository;
  @Autowired
  private EpisodeRepository episodeRepository;

  //  get podcast
  @GetMapping
  public ResponseEntity<List<Podcast>> listPodcast(@RequestParam(defaultValue = "0") Integer page,
                                   @RequestParam(defaultValue = "25") Integer size,
                                   @RequestParam(defaultValue = "collectionId") String sort) {

    List<Podcast> list = service.getAllPodcast(page, size, sort);

    return new ResponseEntity<List<Podcast>>(list, new HttpHeaders(), HttpStatus.OK);
  }

  //  get podcast by ID
  @GetMapping("{id}")
  public ResponseEntity<Podcast> getPodcastByID(
      @PathVariable(value = "id") Long podcastID
  ) throws ResourceNotFoundException {
    Podcast podcast = podcastRepository.findById(podcastID)
        .orElseThrow(() -> new ResourceNotFoundException("Podcast Not Found for ID: " + podcastID));

    return ResponseEntity.ok().body(podcast);
  }

  //  get podcast
  @GetMapping("{id}/episodes")
  public List<Episode> listEpisodes(
      @PathVariable(value = "id") Long podcastID
  ) throws ResourceNotFoundException  {
    System.out.println("podcastID::" + podcastID);
    return episodeRepository.findAllByPodcastId(podcastID);
  }
}

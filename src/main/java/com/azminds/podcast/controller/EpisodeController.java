package com.azminds.podcast.controller;

import com.azminds.podcast.exception.ResourceNotFoundException;
import com.azminds.podcast.model.Episode;
import com.azminds.podcast.model.Podcast;
import com.azminds.podcast.repository.EpisodeRepository;
import com.azminds.podcast.repository.PodcastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class EpisodeController {

  @Autowired
  private EpisodeRepository episodeRepository;

  //  get podcast
  @GetMapping("podcast/{id}/episodes")
  public List<Episode> listEpisodes(
      @PathVariable(value = "id") Long podcastID
  ) throws ResourceNotFoundException  {
    System.out.println("podcastID::" + podcastID);
    return episodeRepository.findAllByPodcastId(podcastID);
  }

  //  get podcast by ID
  @GetMapping("episode/{id}")
  public ResponseEntity<Episode> getPodcastByID(
      @PathVariable(value = "id") Long episodeID
  ) throws ResourceNotFoundException {
    Episode episode = episodeRepository.findById(episodeID)
        .orElseThrow(() -> new ResourceNotFoundException("Podcast Not Found for ID: " + episodeID));

    return ResponseEntity.ok().body(episode);
  }

}

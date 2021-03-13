package com.azminds.podcast.controller;

import com.azminds.podcast.exception.ResourceNotFoundException;
import com.azminds.podcast.model.Podcast;
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
public class PodcastController {

  @Autowired
  private PodcastRepository podcastRepository;

  //  get podcast
  @GetMapping("podcast")
  public List<Podcast> listPodcast() {
    return this.podcastRepository.findAll();
  }

  //  get podcast by ID
  @GetMapping("podcast/{id}")
  public ResponseEntity<Podcast> getPodcastByID(
      @PathVariable(value = "id") Long podcastID
  ) throws ResourceNotFoundException {
    Podcast podcast = podcastRepository.findById(podcastID)
        .orElseThrow(() -> new ResourceNotFoundException("Podcast Not Found for ID: " + podcastID));

    return ResponseEntity.ok().body(podcast);
  }

}

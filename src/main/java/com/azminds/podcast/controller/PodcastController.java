package com.azminds.podcast.controller;

import com.azminds.podcast.exception.ResourceNotFoundException;
import com.azminds.podcast.model.Podcast;
import com.azminds.podcast.repository.PodcastRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class PodcastController {

  private static final Logger logger = LoggerFactory.getLogger(PodcastController.class);

  @Autowired
  private PodcastRepository podcastRepository;

  //  get podcast
  @GetMapping("podcast")
  public Page<Podcast> listPodcast() {
    Pageable pageable = PageRequest.of(0, 25, Sort.by("collectionId"));

    return this.podcastRepository.findAll(pageable);
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

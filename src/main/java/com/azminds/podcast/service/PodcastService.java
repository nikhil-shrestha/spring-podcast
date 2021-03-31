package com.azminds.podcast.service;

import com.azminds.podcast.model.Podcast;
import com.azminds.podcast.repository.PodcastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PodcastService {

  @Autowired
  PodcastRepository repository;

  public List<Podcast> getAllPodcast(Integer pageNo, Integer pageSize, String sortBy) {
    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

    Page<Podcast> pagedResult = repository.findAll(paging);

    if(pagedResult.hasContent()) {
      return pagedResult.getContent();
    } else {
      return new ArrayList<>();
    }
  }
}

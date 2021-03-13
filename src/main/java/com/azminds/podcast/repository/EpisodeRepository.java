package com.azminds.podcast.repository;

import com.azminds.podcast.model.Episode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EpisodeRepository  extends JpaRepository<Episode, Long> {

  List<Episode> findAllByPodcastId(Long collectionId);
}

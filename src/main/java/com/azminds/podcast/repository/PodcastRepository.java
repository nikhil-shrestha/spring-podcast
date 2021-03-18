package com.azminds.podcast.repository;

import com.azminds.podcast.model.Podcast;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PodcastRepository extends PagingAndSortingRepository<Podcast, Long> {
}

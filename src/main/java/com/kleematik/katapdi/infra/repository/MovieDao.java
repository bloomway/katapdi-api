package com.kleematik.katapdi.infra.repository;

import com.kleematik.katapdi.infra.batch.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieDao extends JpaRepository<Movie, Long> {

    Page<Movie> findByOriginalTitleContainsAllIgnoreCase(String query, Pageable pageRequest);
}

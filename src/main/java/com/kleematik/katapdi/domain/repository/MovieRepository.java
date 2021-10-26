package com.kleematik.katapdi.domain.repository;

import com.kleematik.katapdi.domain.dto.MovieDto;

public interface MovieRepository {

    MovieDto findAll(int page);

    MovieDto findByOriginalTitle(String query, int page);
}

package com.kleematik.katapdi.domain.dto;

import com.kleematik.katapdi.domain.movie.Movie;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class MovieDto {
    private final List<Movie> movies;
    private final int currentPage;
    private final int totalPages;
    private final int totalItems;
}

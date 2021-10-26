package com.kleematik.katapdi.application.movie.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class MovieDtoResponse {
    private final int currentPage;
    private final int totalItems;
    private final int totalPages;
    private final List<MovieResponse> movies;
}

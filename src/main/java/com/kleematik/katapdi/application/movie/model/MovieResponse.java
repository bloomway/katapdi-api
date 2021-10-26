package com.kleematik.katapdi.application.movie.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Builder
@Getter
public class MovieResponse {
    private final Long id;
    private final String tconst;
    private final String originalTitle;
    private final String primaryTitle;
    private final String titleType;
    private final boolean isAdult;
    private final String startYear;
    private final String endYear;
    private final String runtimeMinutes;
    private final List<String> genres;

}

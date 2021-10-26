package com.kleematik.katapdi.application.movie.services;

import com.kleematik.katapdi.application.movie.model.MovieDtoResponse;
import com.kleematik.katapdi.application.movie.model.MovieResponse;
import com.kleematik.katapdi.domain.movie.Movie;
import com.kleematik.katapdi.domain.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class GetMovies {

    private final MovieRepository movieRepository;

    public MovieDtoResponse execute(String query, int page) {

        final var movieResponseBuilder = MovieDtoResponse.builder();

        final var movieResponses = Stream.of(movieRepository.findByOriginalTitle(query, page))
                .peek(dto ->
                        movieResponseBuilder.currentPage(dto.getCurrentPage())
                                .totalItems(dto.getTotalItems())
                                .totalPages(dto.getTotalPages())
                )
                .flatMap(dto -> dto.getMovies().stream())
                .map(movie -> MovieResponse.builder()
                        .id(movie.getId())
                        .tconst(movie.getTconst().getValue())
                        .titleType(movie.getTitle().getType())
                        .startYear(movie.getRelease().getStart())
                        .endYear(movie.getRelease().getEnd())
                        .genres(
                                movie.getGenres().stream()
                                        .map(Movie.Genre::getValue)
                                        .collect(Collectors.toList())
                        )
                        .isAdult(movie.isAdult())
                        .originalTitle(movie.getTitle().getOriginal())
                        .primaryTitle(movie.getTitle().getPrimary())
                        .runtimeMinutes(movie.getDuration().getValue())
                        .build()
                )
                .collect(Collectors.toUnmodifiableList());
        return movieResponseBuilder.movies(movieResponses).build();
    }

}

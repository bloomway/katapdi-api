package com.kleematik.katapdi.infra.repository;

import com.kleematik.katapdi.domain.dto.MovieDto;
import com.kleematik.katapdi.domain.repository.MovieRepository;
import com.kleematik.katapdi.infra.batch.model.Movie;
import com.kleematik.katapdi.infra.configuration.ApiMovieProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MovieRepositoryImpl implements MovieRepository {

    private final MovieDao dao;
    private final ApiMovieProperties apiMovieProperties;

    @Override
    public MovieDto findByOriginalTitle(String query, int page) {
        final var pageRequest = PageRequest.of(page, apiMovieProperties.getLimit());
        final var chunkPage = dao.findByOriginalTitleContainsAllIgnoreCase(query, pageRequest);
        return transform(chunkPage);
    }

    @Override
    public MovieDto findAll(int page) {
        final var pageRequest = PageRequest.of(page, apiMovieProperties.getLimit());
        final var chunkPage = dao.findAll(pageRequest);
        return transform(chunkPage);
    }

    private MovieDto transform(Page<Movie> page) {
        final var movieDtoBuilder = MovieDto.builder();
        movieDtoBuilder
                .currentPage(page.getNumber())
                .totalPages(page.getTotalPages())
                .totalItems(page.getNumberOfElements());

        final var movies = page.stream().map(movieData -> {

            final var title = com.kleematik.katapdi.domain.movie.Movie.Title.builder()
                    .original(movieData.getOriginalTitle())
                    .primary(movieData.getPrimaryTitle())
                    .type(movieData.getTitleType())
                    .build();

            final var tconst = com.kleematik.katapdi.domain.movie.Movie.Tconst.of(movieData.getTconst());
            final var duration = com.kleematik.katapdi.domain.movie.Movie.Duration.of(movieData.getRuntimeMinutes());
            final var release = com.kleematik.katapdi.domain.movie.Movie.Release.builder()
                    .start(movieData.getStartYear())
                    .end("\\N".hashCode() == movieData.getEndYear().toUpperCase().hashCode() ?
                            movieData.getEndYear() : "")
                    .build();

            final var genres = List.of(movieData.getGenres().split(","))
                    .stream()
                    .map(com.kleematik.katapdi.domain.movie.Movie.Genre::of)
                    .collect(Collectors.toUnmodifiableList());

            return com.kleematik.katapdi.domain.movie.Movie.builder()
                    .tconst(tconst)
                    .id(movieData.getId())
                    .title(title)
                    .duration(duration)
                    .release(release)
                    .genres(genres)
                    .isAdult(movieData.isAdult())
                    .build();
        }).collect(Collectors.toUnmodifiableList());

        return movieDtoBuilder.movies(movies).build();
    }
}

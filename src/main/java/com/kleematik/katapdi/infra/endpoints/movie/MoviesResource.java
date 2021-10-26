package com.kleematik.katapdi.infra.endpoints.movie;

import com.kleematik.katapdi.application.movie.services.GetMovies;
import com.kleematik.katapdi.infra.endpoints.model.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping(MoviesResource.MOVIE_URL)
public class MoviesResource {

    public static final String MOVIE_URL = "/api/movies";

    private final GetMovies getMovies;

    @GetMapping
    public ResponseEntity<ApiResponse> execute(
            @RequestParam(value = "k", defaultValue = "") String query ,
            @RequestParam(value = "p", defaultValue = "0") int page
    ) {
        final var movies = getMovies.execute(query, page);
        final var response = ApiResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .status(HttpStatus.OK.getReasonPhrase())
                .data(Map.of("value", movies))
                .timestamp(System.currentTimeMillis())
                .build();
        return ResponseEntity.of(Optional.of(response));
    }
}

package com.kleematik.katapdi.infra.batch;

import com.kleematik.katapdi.infra.batch.model.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;


public class LoadMovieStep {

    private final ItemReader<Movie> movieItemReader;
    private final ItemWriter<Movie> movieItemWriter;
    private final StepBuilderFactory builderFactory;

    public LoadMovieStep(ItemReader<Movie> movieItemReader, ItemWriter<Movie> movieItemWriter, StepBuilderFactory builderFactory) {
        this.movieItemReader = movieItemReader;
        this.movieItemWriter = movieItemWriter;
        this.builderFactory = builderFactory;
    }
}

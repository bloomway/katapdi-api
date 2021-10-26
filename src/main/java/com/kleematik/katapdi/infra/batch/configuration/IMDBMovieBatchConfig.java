package com.kleematik.katapdi.infra.batch.configuration;

import com.kleematik.katapdi.infra.batch.IMDBBatchReader;
import com.kleematik.katapdi.infra.batch.IMDBBatchWriter;
import com.kleematik.katapdi.infra.batch.IMDBFlatFileItemReader;
import com.kleematik.katapdi.infra.batch.listener.IMDBJobListener;
import com.kleematik.katapdi.infra.batch.model.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
//@Configuration
//@EnableBatchProcessing
public class IMDBMovieBatchConfig {

    private static final String JOB_NAME = "MOVIE__LAUNCHER";
    private static final String STEP_NAME = "LOAD__MOVIE";
    public static final String MOVIE_LAUNCHER_JOB = "MOVIE_LAUNCHER_JOB";

    private final BatchProperties batchProperties;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final IMDBJobListener imdbJobListener;
    private final IMDBBatchWriter imdbBatchWriter;


    //@Bean
    //@Qualifier(MOVIE_LAUNCHER_JOB)
    public Job movieLauncherJob() {
        return jobBuilderFactory.get(JOB_NAME)
                .incrementer(new RunIdIncrementer())
                .listener(imdbJobListener)
                .flow(loadMovieStep(itemReader(), imdbBatchWriter))
                .end()
                .build();
    }

    //@Bean
    public Step loadMovieStep(
            ItemReader<Movie> movieItemReader,
            ItemWriter<Movie> movieItemWriter
    ) {
        return stepBuilderFactory.get(STEP_NAME)
                .<Movie, Movie>chunk(Integer.parseInt(batchProperties.getChunkSize()))
                .reader(movieItemReader)
                .writer(movieItemWriter)
                .faultTolerant()
                //.taskExecutor(taskExecutor)
                .build();
    }

    //@Bean
    public ItemReader<Movie> itemReader() {
        return new IMDBBatchReader(batchProperties.getWorkDir(), flatFileItemReader());
    }

    //@Bean
    public IMDBFlatFileItemReader.LineMapper lineMapper() {
        return new IMDBFlatFileItemReader.LineMapper();
    }

    //@Bean
    public IMDBFlatFileItemReader flatFileItemReader() {
        return new IMDBFlatFileItemReader(lineMapper());
    }

}

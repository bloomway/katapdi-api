package com.kleematik.katapdi.infra.batch;

import com.kleematik.katapdi.infra.batch.model.Movie;
import com.kleematik.katapdi.infra.repository.MovieDao;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class IMDBBatchWriter implements ItemWriter<Movie> {

    private final MovieDao movieDao;

    @Override
    public void write(List<? extends Movie> movies) throws Exception {
        movieDao.saveAll(movies);
    }
}

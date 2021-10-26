package com.kleematik.katapdi.infra.batch.executor;

import com.kleematik.katapdi.infra.batch.configuration.BatchProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Slf4j
@RequiredArgsConstructor
//@Configuration
public class IMDBMovieThreadExecutorConfiguration {

    public static final String THREAD_PREFIX = "MovieBatchThread-";
    public static final int POOL_SIZE = 10;
    public static final int QUEUE_CAPACITY = 250;
    public static final String MOVIE_TASK_EXECUTOR = "MOVIE_TASK_EXECUTOR";

    private final BatchProperties batchProperties;

    //@Bean(name = "movieBatchExecutor")
   // @Qualifier(MOVIE_TASK_EXECUTOR)
    public ThreadPoolTaskExecutor executor() {
        log.info("Creating MovieBatch ThreadExecutor");

        final var executor = new ThreadPoolTaskExecutor();

        final var threadProperties = batchProperties.getThread();

        final var corePoolSize = threadProperties.getCorePoolSize() != null ?
                threadProperties.getCorePoolSize() : POOL_SIZE;
        executor.setCorePoolSize(corePoolSize);

        final var maxPoolSize = threadProperties.getMaxPoolSize() != null ?
                threadProperties.getMaxPoolSize() : POOL_SIZE;
        executor.setMaxPoolSize(maxPoolSize);

        final var prefix = threadProperties.getPrefix() == null ?
                THREAD_PREFIX :
                threadProperties.getPrefix();
        executor.setThreadNamePrefix(prefix);

        final var queueCapacity = threadProperties.getQueueCapacity() != null ?
                threadProperties.getQueueCapacity() : QUEUE_CAPACITY;
        executor.setQueueCapacity(queueCapacity);

        executor.initialize();
        return executor;
    }
}

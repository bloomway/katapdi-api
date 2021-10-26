package com.kleematik.katapdi.infra.batch.service;

import com.kleematik.katapdi.infra.batch.configuration.IMDBMovieBatchConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
//@Service
public class IMDBMovieJobLauncherService {

  //  @Autowired
    private JobLauncher jobLauncher;

    //@Autowired
    //@Qualifier(IMDBMovieBatchConfig.MOVIE_LAUNCHER_JOB)
    private Job job;

    public BatchStatus execute() throws JobInstanceAlreadyCompleteException,
            JobExecutionAlreadyRunningException, JobParametersInvalidException,
            JobRestartException {

        log.info("launching job: " + job.getName() + "...");

        final var parameters =
                Map.of("time", new JobParameter(System.currentTimeMillis()));

        final var jobParameters = new JobParameters(parameters);
        final var jobExecution = jobLauncher.run(job, jobParameters);

        return jobExecution.getStatus();
    }
}

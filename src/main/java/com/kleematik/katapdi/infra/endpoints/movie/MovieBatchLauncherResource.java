package com.kleematik.katapdi.infra.endpoints.movie;

import com.kleematik.katapdi.infra.batch.service.IMDBMovieJobLauncherService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/batch")
public class MovieBatchLauncherResource {

    private final IMDBMovieJobLauncherService imdbMovieJobLauncherService;

    @GetMapping("/movie")
    @ResponseBody
    public BatchStatus execute() throws JobInstanceAlreadyCompleteException,
            JobExecutionAlreadyRunningException, JobParametersInvalidException,
            JobRestartException {
       return imdbMovieJobLauncherService.execute();
    }
}

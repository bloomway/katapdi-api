package com.kleematik.katapdi.infra.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class IMDBJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
      log.info(String.format("Beginning of the listener #%s# at %s",
              jobExecution.getJobInstance().getJobName(),
              jobExecution.getStartTime()));
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info(String.format("End of the listener #%s# at %s with status [%s]",
                jobExecution.getJobInstance().getJobName(),
                jobExecution.getEndTime(),
                jobExecution.getExitStatus().getExitCode()));
    }
}

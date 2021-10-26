package com.kleematik.katapdi.infra.batch.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@NoArgsConstructor
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.batch")
public class BatchProperties {
    private String workDir;
    private String chunkSize;
    private ThreadProperties thread;

    @NoArgsConstructor
    @Getter
    @Setter
    public static class ThreadProperties {
        private Integer corePoolSize;
        private Integer maxPoolSize;
        private Integer queueCapacity;
        private String prefix;
    }

}

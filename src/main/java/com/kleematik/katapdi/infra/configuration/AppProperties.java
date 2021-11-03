package com.kleematik.katapdi.infra.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private EnvProperties env;

    @NoArgsConstructor
    @Getter
    @Setter
    public static class EnvProperties {
        private boolean production = false;
    }
}

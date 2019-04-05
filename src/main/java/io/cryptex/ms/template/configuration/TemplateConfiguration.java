package io.cryptex.ms.template.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class TemplateConfiguration {

    @Bean
    public Clock utcClock() {
        return Clock.systemUTC();
    }
}

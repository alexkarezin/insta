package io.cryptex.ms.template.configuration;

import io.cryptex.ms.template.exception.integration.CommunicationErrorHandler;
import io.cryptex.ms.template.integration.properties.CommunicationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder();
    }

    @Bean(name = "communicationRestTemplate")
    public RestTemplate communicationRestTemplate(RestTemplateBuilder restTemplateBuilder, CommunicationProperties communicationProperties) {
        restTemplateBuilder = restTemplateBuilder.errorHandler(new CommunicationErrorHandler())
                .rootUri(communicationProperties.getUri())
                .setConnectTimeout(Duration.ofMillis(communicationProperties.getTimeout().getConnect()))
                .setReadTimeout(Duration.ofMillis(communicationProperties.getTimeout().getRead()));

        return restTemplateBuilder.build();
    }
}

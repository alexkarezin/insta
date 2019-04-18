package io.cryptex.ms.template.integration.properties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "ms.communication")
public class CommunicationProperties {

    private String uri;
    private Path path;
    private Timeout timeout;

    @Getter
    @Setter
    @NoArgsConstructor
    @ConfigurationProperties(prefix = "path")
    public static class Path {
        private String link;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @ConfigurationProperties(prefix = "timeout")
    public static class Timeout {
        private int connect;
        private int read;

    }
}

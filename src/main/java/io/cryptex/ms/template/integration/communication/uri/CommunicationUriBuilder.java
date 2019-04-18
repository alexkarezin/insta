package io.cryptex.ms.template.integration.communication.uri;

import io.cryptex.ms.template.integration.properties.CommunicationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class CommunicationUriBuilder {

    private final CommunicationProperties communicationProperties;

    public URI getLinkURI() {
        return UriComponentsBuilder.fromUriString(communicationProperties.getUri())
                .path(communicationProperties.getPath().getLink())
                .build()
                .encode()
                .toUri();
    }
}

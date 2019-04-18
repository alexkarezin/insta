package io.cryptex.ms.template.utils;

import io.cryptex.ms.template.db.model.Template;
import io.cryptex.ms.template.integration.communication.dto.CommunicationDto;
import io.cryptex.ms.template.web.model.TemplateRequest;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class TemplateTestUtil {
    public static final long TIME_DEFAULT = 1479250540110L;
    public static final String VALUE_1_DEFAULT = ":First value:";
    public static final String VALUE_2_DEFAULT = ":Second value:";
    public static final String DOPOST_URI_DEFAULT = "/api/dopost";

    public static TemplateRequest createTemplateRequestDefault() {
        return TemplateRequest.builder()
                .value(VALUE_1_DEFAULT)
                .build();
    }

    public static CommunicationDto createCommunicationDtoDefault() {
        return CommunicationDto.builder()
                .value(VALUE_2_DEFAULT)
                .expired(true)
                .build();
    }

    public static Template createTemplateDefault() {
        return Template.builder()
                .timeCreated(TIME_DEFAULT)
                .timeUpdated(TIME_DEFAULT)
                .value(VALUE_1_DEFAULT)
                .build();
    }

    public static URI buildDoPostUri() {
        return UriComponentsBuilder
                .fromPath(DOPOST_URI_DEFAULT)
                .build()
                .encode()
                .toUri();
    }
}

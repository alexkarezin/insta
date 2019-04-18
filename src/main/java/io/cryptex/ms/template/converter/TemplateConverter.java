package io.cryptex.ms.template.converter;

import io.cryptex.ms.template.db.model.Template;
import io.cryptex.ms.template.dto.TemplateDto;
import io.cryptex.ms.template.web.model.TemplateRequest;
import io.cryptex.ms.template.web.model.TemplateResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.Clock;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TemplateConverter {

    public static Template templateRequestToModel(@NotNull TemplateRequest templateRequest, Clock clock) {
        long currentTime = clock.millis();
        return Template.builder()
                .value(templateRequest.getValue())
                .timeCreated(currentTime)
                .timeUpdated(currentTime)
                .build();
    }

    public static TemplateResponse templateToResponse(@NotNull Template template) {
        return TemplateResponse.builder()
                .value(template.getValue())
                .build();
    }

    public static TemplateDto templateToTemplateDto(@NotNull Template template) {
        return TemplateDto.builder()
                .value(template.getValue())
                .build();
    }
}

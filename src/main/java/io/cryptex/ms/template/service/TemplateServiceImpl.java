package io.cryptex.ms.template.service;

import io.cryptex.ms.template.converter.TemplateConverter;
import io.cryptex.ms.template.db.model.Template;
import io.cryptex.ms.template.db.repository.TemplateRepository;
import io.cryptex.ms.template.integration.communication.dto.CommunicationDto;
import io.cryptex.ms.template.dto.TemplateDto;
import io.cryptex.ms.template.exception.NoSuchValueException;
import io.cryptex.ms.template.integration.communication.rest.CommunicationServiceInterface;
import io.cryptex.ms.template.web.model.TemplateRequest;
import io.cryptex.ms.template.web.model.TemplateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Clock;

@Slf4j
@RequiredArgsConstructor
@Service
public class TemplateServiceImpl implements TemplateService {

    private final TemplateRepository templateRepository;
    private final CommunicationServiceInterface communicationService;
    private final Clock utcClock;

    @Override
    public TemplateResponse templateMethod(TemplateRequest templateRequest) {
        Template template = TemplateConverter.templateRequestToModel(templateRequest, utcClock);
        template = templateRepository.save(template);
        TemplateDto templateDto = TemplateConverter.templateToTemplateDto(template);
        CommunicationDto communicationDto = communicationService.postTemplateDto(templateDto);
        String value = template.getValue();
        template = templateRepository.findByValue(value).orElseThrow(() -> {
            log.error("No such value: value = {}", value);
            return new NoSuchValueException(String.format("No such value: value = %s", value));
        });
        if (communicationDto.getExpired()) {
            template.setValue(template.getValue() + " " +  communicationDto.getValue());
            template.setTimeUpdated(utcClock.millis());
            templateRepository.save(template);
        }
        return TemplateConverter.templateToResponse(template);
    }
}

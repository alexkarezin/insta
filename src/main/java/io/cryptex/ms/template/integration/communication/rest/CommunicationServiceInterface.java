package io.cryptex.ms.template.integration.communication.rest;

import io.cryptex.ms.template.integration.communication.dto.CommunicationDto;
import io.cryptex.ms.template.dto.TemplateDto;
import io.cryptex.ms.template.integration.rest.CommunicationService;

import java.util.List;

public interface CommunicationServiceInterface extends CommunicationService {

    List<CommunicationDto> getCommunicationDtoList();
    CommunicationDto postTemplateDto(TemplateDto templateDto);
}

package io.cryptex.ms.template.integration.communication.rest;

import io.cryptex.ms.template.integration.communication.dto.CommunicationDto;
import io.cryptex.ms.template.dto.TemplateDto;
import io.cryptex.ms.template.exception.InnerServiceException;
import io.cryptex.ms.template.integration.communication.uri.CommunicationUriBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommunicationServiceImpl implements CommunicationServiceInterface {

    private final RestTemplate communicationRestTemplate;
    private final CommunicationUriBuilder communicationUriBuilder;

    @Override
    public List<CommunicationDto> getCommunicationDtoList() {
        HttpEntity emptyEntity = new HttpEntity<>(buildDefaultHttpHeaders());
        URI uri = communicationUriBuilder.getLinkURI();
        try {
            log.info("Request to get communication list send to communication ms.");
            List<CommunicationDto> communicationDtoList = communicationRestTemplate.exchange(uri, HttpMethod.GET, emptyEntity, new ParameterizedTypeReference<List<CommunicationDto>>(){})
                    .getBody();
            log.info("Response from communication ms: communication list isEmpty = {}", communicationDtoList.isEmpty());
            return communicationDtoList;
        } catch (ResourceAccessException e) {
            log.error("No response from communication ms on get communication list request. Error: {} ", e.getMessage());
            throw new InnerServiceException(String.format("No response from communication ms on get communication list request. Error: %s", e.getMessage()), e);
        }
    }

    @Override
    public CommunicationDto postTemplateDto(TemplateDto templateDto) {
        HttpEntity entity = new HttpEntity<>(templateDto, buildDefaultHttpHeaders());
        URI uri = communicationUriBuilder.getLinkURI();
        try {
            log.info("Request to post template dto send to communication ms: template dto value = {}.", templateDto.getValue());
            CommunicationDto communicationDto = communicationRestTemplate.exchange(uri, HttpMethod.POST, entity, CommunicationDto.class)
                    .getBody();
            log.info("Response from communication ms on post template dto request: template value = {}, communication value = {}",
                    templateDto.getValue(), communicationDto.getValue());
            return communicationDto;
        } catch (ResourceAccessException e) {
            log.error("No response from communication ms on post template dto request: template dto value = {}. Error: {} ",
                    templateDto.getValue(), e.getMessage());
            throw new InnerServiceException(String.format("No response from communication ms on get communication list request: template dto value = %s. Error: %s",
                    templateDto.getValue(), e.getMessage()), e);
        }
    }
}

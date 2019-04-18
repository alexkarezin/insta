package io.cryptex.ms.template.web.controller;

import io.cryptex.ms.template.web.controller.interfaces.TemplateController;
import io.cryptex.ms.template.service.TemplateService;
import io.cryptex.ms.template.web.model.TemplateRequest;
import io.cryptex.ms.template.web.model.TemplateResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@AllArgsConstructor
@RestController
public class TemplateControllerImpl implements TemplateController {

    private final TemplateService templateService;

    @Override
    public ResponseEntity<TemplateResponse> doPost(@NotNull @Valid @RequestBody TemplateRequest templateRequest) {
        log.info("Receive request: value = {}", templateRequest.getValue());
        TemplateResponse templateResponse = templateService.templateMethod(templateRequest);
        log.info("Send response:" );
        return ResponseEntity.ok(templateResponse);
    }
}

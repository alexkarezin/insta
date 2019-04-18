package io.cryptex.ms.template.web.controller.interfaces;

import io.cryptex.ms.template.web.model.TemplateRequest;
import io.cryptex.ms.template.web.model.TemplateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@Validated
@RequestMapping("/api/dopost")
public interface TemplateController {

    @PostMapping
    ResponseEntity<TemplateResponse> doPost(@NotNull @Valid @RequestBody TemplateRequest templateRequest);
}

package io.cryptex.ms.template.service;

import io.cryptex.ms.template.web.model.TemplateRequest;
import io.cryptex.ms.template.web.model.TemplateResponse;

public interface TemplateService {
    TemplateResponse templateMethod(TemplateRequest templateRequest);
}
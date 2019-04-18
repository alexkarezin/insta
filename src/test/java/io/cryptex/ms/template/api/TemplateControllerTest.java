package io.cryptex.ms.template.api;

import io.cryptex.ms.template.db.model.Template;
import io.cryptex.ms.template.db.repository.TemplateRepository;
import io.cryptex.ms.template.integration.communication.uri.CommunicationUriBuilder;
import io.cryptex.ms.template.utils.TemplateTestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class TemplateControllerTest extends BaseApiTest {

    @Autowired
    private CommunicationUriBuilder communicationUriBuilder;

    @Autowired
    protected RestTemplate communicationRestTemplate;
    MockRestServiceServer communicationServiceServer;

    @MockBean
    protected TemplateRepository templateRepository;

    @Before
    public void setUp() {
        this.communicationServiceServer = MockRestServiceServer.bindTo(communicationRestTemplate)
                .ignoreExpectOrder(true)
                .build();
        super.setUp();
    }

    @Test
    public void shouldReturnWhenPostTemplateRequestThenTemplateResponseOk() throws Exception {

        Optional<Template> optionalTemplate = Optional.of(TemplateTestUtil.createTemplateDefault());

        when(utcClock.millis()).thenReturn(TemplateTestUtil.TIME_DEFAULT);
        when(templateRepository.save(any())).thenReturn(TemplateTestUtil.createTemplateDefault());
        when(templateRepository.findByValue(any())).thenReturn(optionalTemplate);
        mockResponseWithSuccess(communicationServiceServer, HttpMethod.POST, communicationUriBuilder.getLinkURI().toString(), TemplateTestUtil.createCommunicationDtoDefault());

        performPostInteraction(TemplateTestUtil.buildDoPostUri(), TemplateTestUtil.createTemplateRequestDefault())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.value").value(TemplateTestUtil.VALUE_1_DEFAULT + " " + TemplateTestUtil.VALUE_2_DEFAULT));

        verify(templateRepository, times(2)).save(any());
    }
}

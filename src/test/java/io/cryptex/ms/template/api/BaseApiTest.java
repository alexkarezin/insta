package io.cryptex.ms.template.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;
import java.time.Clock;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
public abstract class BaseApiTest {

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected Clock utcClock;
    private MockMvc mockMvc;

    protected void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    protected ResultActions performGetInteraction(URI uri) throws Exception {
        return this.mockMvc.perform(get(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON_VALUE));
    }

    protected ResultActions performPostInteraction(URI uri, Object dataRequest) throws Exception {
        return this.mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dataRequest))
                .accept(MediaType.APPLICATION_JSON_VALUE));
    }

    protected void mockPostResponseWithHttpStatusOk(MockRestServiceServer mockRestServiceServer, String uri) {
        mockRestServiceServer.expect(ExpectedCount.manyTimes(), requestTo(uri))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withStatus(HttpStatus.OK));
    }
    protected void mockResponseWithSuccess(MockRestServiceServer mockRestServiceServer, HttpMethod httpMethod, String uri, Object dataResponse) throws Exception {
        String stringifiedDataResponse = objectMapper.writeValueAsString(dataResponse);
        mockRestServiceServer.expect(ExpectedCount.manyTimes(), requestTo(uri))
                .andExpect(method(httpMethod))
                .andRespond(withSuccess(stringifiedDataResponse, MediaType.APPLICATION_JSON));
    }
}

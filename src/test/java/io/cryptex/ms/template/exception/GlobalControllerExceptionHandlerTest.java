package io.cryptex.ms.template.exception;

import io.cryptex.ms.template.dto.ErrorInfoDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GlobalControllerExceptionHandlerTest {

    @InjectMocks
    private GlobalControllerExceptionHandler exceptionHandler;

    @Test
    public void shouldHandleInnerServiceException() {
        InnerServiceException exception = new InnerServiceException("test message");
        ResponseEntity<ErrorInfoDto> entity = exceptionHandler.handleBaseException(exception, new MockHttpServletRequest());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, entity.getStatusCode());
        assertEquals("test message", entity.getBody().getMessages().get(0));
        assertEquals(ExceptionCode.INTERNAL_SERVICE_EXCEPTION.getCode(), entity.getBody().getCode().intValue());
    }

    @Test
    public void shouldHandleRuntimeException() {
        IllegalArgumentException exception = new IllegalArgumentException("parameter is not valid");
        ResponseEntity<ErrorInfoDto> entity = exceptionHandler.handleRuntimeException(exception, new MockHttpServletRequest());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, entity.getStatusCode());
        assertEquals("parameter is not valid", entity.getBody().getMessages().get(0));
        assertEquals(ExceptionCode.UNKNOWN_EXCEPTION.getCode(), entity.getBody().getCode().intValue());
    }
}
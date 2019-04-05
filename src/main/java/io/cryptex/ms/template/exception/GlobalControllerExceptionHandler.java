package io.cryptex.ms.template.exception;

import io.cryptex.ms.template.integration.handler.RemotePassThroughException;
import io.cryptex.ms.template.web.model.ErrorInfoDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Slf4j
@AllArgsConstructor
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    private static final String MESSAGE_PREFIX = " | Message: ";

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorInfoDto> handleBaseException(BaseException e, HttpServletRequest request) {
        ErrorInfoDto errorInfoDto = createErrorDto(e);

        log(request, e);
        return new ResponseEntity<>(errorInfoDto, e.getExceptionCode().getHttpStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        ErrorInfoDto errorInfoDto = createErrorDto(ExceptionCode.UNKNOWN_EXCEPTION, e.getMessage());
        logError(request, e);

        return new ResponseEntity<>(errorInfoDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RemotePassThroughException.class)
    public ResponseEntity handleRemotePassThroughException(RemotePassThroughException e, HttpServletRequest request) {
        ErrorInfoDto errorInfoDto = e.getErrorInfoDto();
        logWarning(request, e.getMessage());

        return new ResponseEntity<>(errorInfoDto, HttpStatus.valueOf(errorInfoDto.getStatus()));
    }

    private void log(HttpServletRequest request, BaseException baseException) {
        String message = request.getRequestURI() + "?" + request.getQueryString() + MESSAGE_PREFIX + baseException.getMessage();
        if (baseException.getExceptionCode().getHttpStatus() == HttpStatus.INTERNAL_SERVER_ERROR) {
            log.error(message);
        } else {
            log.warn(message);
        }
    }

    private static ErrorInfoDto createErrorDto(BaseException baseException) {
        ExceptionCode exceptionCode = baseException.getExceptionCode();
        ErrorInfoDto errorInfoDto = new ErrorInfoDto();
        errorInfoDto.setCode(exceptionCode.getCode());
        errorInfoDto.setStatus(exceptionCode.getHttpStatus().value());
        errorInfoDto.setMessages(Collections.singletonList(baseException.getMessage()));

        return errorInfoDto;
    }

    private static ErrorInfoDto createErrorDto(ExceptionCode exceptionCode, String message) {
        ErrorInfoDto errorInfoDto = new ErrorInfoDto();
        errorInfoDto.setCode(exceptionCode.getCode());
        errorInfoDto.setStatus(exceptionCode.getHttpStatus().value());
        errorInfoDto.setMessages(Collections.singletonList(message));

        return errorInfoDto;
    }

    private void logError(HttpServletRequest request, RuntimeException exception) {
        String message = request.getRequestURI() + "?" + request.getQueryString();
        log.error(message, exception);
    }

    private void logWarning(HttpServletRequest request, String errorMessage) {
        String message = request.getRequestURI() + "?" + request.getQueryString() + MESSAGE_PREFIX + errorMessage;
        log.warn(message);
    }
}

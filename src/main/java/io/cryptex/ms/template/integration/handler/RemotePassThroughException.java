package io.cryptex.ms.template.integration.handler;

import io.cryptex.ms.template.web.model.ErrorInfoDto;

public class RemotePassThroughException extends RuntimeException {

    private final ErrorInfoDto errorInfoDto;

    public RemotePassThroughException(ErrorInfoDto errorInfoDto) {
        super(errorInfoDto.getMessages().isEmpty() ? "Unknown error message" : errorInfoDto.getMessages().get(0));
        this.errorInfoDto = errorInfoDto;
    }

    public ErrorInfoDto getErrorInfoDto() {
        return errorInfoDto;
    }
}

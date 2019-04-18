package io.cryptex.ms.template.exception.integration;

import io.cryptex.ms.template.constants.ServiceName;
import io.cryptex.ms.template.dto.ErrorInfoDto;

public class CommunicationErrorHandler extends BasicResponseErrorHandler {

    @Override
    String getServiceName() {
        return ServiceName.COMMUNICATION.getName();
    }

    @Override
    void handleError(ErrorInfoDto errorInfoDto) {
        throw new RemotePassThroughException(errorInfoDto);
    }
}

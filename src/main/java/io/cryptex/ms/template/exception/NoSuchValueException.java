package io.cryptex.ms.template.exception;


public class NoSuchValueException extends BaseException {

    public NoSuchValueException(String message, Throwable throwable) {
        super(ExceptionCode.NO_SUCH_VALUE_EXCEPTION, message, throwable);
    }

    public NoSuchValueException(String message) {
        super(ExceptionCode.NO_SUCH_VALUE_EXCEPTION, message);
    }
}

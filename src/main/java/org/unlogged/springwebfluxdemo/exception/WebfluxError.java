package org.unlogged.springwebfluxdemo.exception;

import reactor.util.annotation.NonNull;

import java.text.MessageFormat;

public enum WebfluxError {

    CUSTOM_THROW(500, 0, "Custom error throw", ErrorTypes.CUSTOM_THROW);

    private final Integer errorCode;
    private final Integer appErrorCode;
    private final String message;
    private final ErrorTypes errorType;

    WebfluxError(Integer errorCode, Integer appErrorCode,
                 String message, @NonNull ErrorTypes errorType) {
        this.errorCode = errorCode;
        this.appErrorCode = appErrorCode;
        this.message = message;
        this.errorType = errorType;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public Integer getAppErrorCode() {
        return appErrorCode;
    }

    public String getMessage(Object... args) {
        return new MessageFormat(this.message).format(args);
    }

    public ErrorTypes getErrorType() {
        return errorType;
    }
}

package org.unlogged.springwebfluxdemo.exception;

import org.slf4j.MDC;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseException extends RuntimeException {

    private Map<String, String> contextMap;

    public BaseException(String message) {
        super(message);
        contextMap = MDC.getCopyOfContextMap();
        if (contextMap == null) {
            contextMap = new HashMap<>();
        }
    }

    public abstract Integer getErrorCode();

    public abstract Integer getAppErrorCode();

    public abstract String getMessage(Object... args);

    public abstract ErrorTypes getErrorType();
}

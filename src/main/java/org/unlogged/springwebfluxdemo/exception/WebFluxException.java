package org.unlogged.springwebfluxdemo.exception;

public class WebFluxException extends BaseException {
    private final WebfluxError error;
    private final transient Object[] args;

    public WebFluxException(WebfluxError error, Object... args) {
        super(error.getMessage());
        this.error = error;
        this.args = args;
    }


    @Override
    public Integer getErrorCode() {
        return this.error.getErrorCode();
    }

    @Override
    public Integer getAppErrorCode() {
        return this.error.getAppErrorCode();
    }

    @Override
    public String getMessage(Object... args) {
        return this.error.getMessage();
    }

    @Override
    public ErrorTypes getErrorType() {
        return this.error.getErrorType();
    }
}

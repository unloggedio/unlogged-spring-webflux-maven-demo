package org.unlogged.springwebfluxdemo.security.model.reqrespModel;

public interface IReqRespModel<T> {
    T getData();
    String getMessage();
}

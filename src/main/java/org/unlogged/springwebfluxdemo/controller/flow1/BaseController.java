package org.unlogged.springwebfluxdemo.controller.flow1;

import org.unlogged.springwebfluxdemo.service.BaseService;

public abstract class BaseController<S extends BaseService> {
    protected S service;

    public boolean baseServe() {
        return service.serve();
    }
}


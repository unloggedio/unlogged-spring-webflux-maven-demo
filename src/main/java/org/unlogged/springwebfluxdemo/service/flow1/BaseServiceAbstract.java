package org.unlogged.springwebfluxdemo.service.flow1;

import org.unlogged.springwebfluxdemo.repository.flow1.BaseRepository;

public class BaseServiceAbstract<R, SR> {

    protected R repository;
    protected SR sqlRepository;
}

package org.unlogged.springwebfluxdemo.enrich.nonreactive;

import reactor.core.publisher.Mono;

public interface EnricherV1<T> {
       T enrich(T obj);
    }
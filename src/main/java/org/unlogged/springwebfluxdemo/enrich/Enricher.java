package org.unlogged.springwebfluxdemo.enrich;

import org.unlogged.springwebfluxdemo.model.Person;
import reactor.core.publisher.Mono;

public interface Enricher<T> {
       Mono<T> enrich(T obj);
    }
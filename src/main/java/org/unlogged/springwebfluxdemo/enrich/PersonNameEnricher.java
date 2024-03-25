package org.unlogged.springwebfluxdemo.enrich;

import org.unlogged.springwebfluxdemo.model.Person;
import reactor.core.publisher.Mono;

public class PersonNameEnricher implements Enricher<Person> {

    @Override
    public Mono<Person> enrich(Person obj) {
        obj.setName(obj.getName() + "#En");
        return Mono.just(obj);
    }
}

package org.unlogged.springwebfluxdemo.enrich;

import org.unlogged.springwebfluxdemo.model.Person;
import reactor.core.publisher.Mono;

import java.util.List;

public class CompositeEnricher implements Enricher<Person> {

    private final List<Enricher> enrichers;

    public CompositeEnricher(List<Enricher> enrichers) {
        this.enrichers = enrichers;
    }


    @Override
    public Mono<Person> enrich(Person obj) {
        return Mono.just(obj)
                .map(person -> {
                    enrichers.forEach(enricher -> enricher.enrich(person));
                    return person;
                });
    }
}
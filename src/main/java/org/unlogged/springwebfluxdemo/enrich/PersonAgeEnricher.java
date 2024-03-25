package org.unlogged.springwebfluxdemo.enrich;

import org.unlogged.springwebfluxdemo.model.Person;
import reactor.core.publisher.Mono;

public class PersonAgeEnricher implements Enricher<Person> {
    @Override
    public Mono<Person> enrich(Person obj) {
        return Mono.just(obj).map(person ->
        {
            person.setAge(person.getAge() + 1);
            return person;
        });
    }
}

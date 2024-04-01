package org.unlogged.springwebfluxdemo.enrich;

import org.unlogged.springwebfluxdemo.model.Person;
import reactor.core.publisher.Mono;

public class PersonAgeEnricher implements Enricher<Person> {
    @Override
    public Mono<Person> enrich(Person obj) {
        return Mono.just(obj).map(person ->
        {
            System.out.println("L2 A call : " + obj);
            person.setAge(person.getAge() + 1);
            return person;
        });
    }
}

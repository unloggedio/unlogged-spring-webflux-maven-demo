package org.unlogged.springwebfluxdemo.enrich.nonreactive;

import org.unlogged.springwebfluxdemo.enrich.Enricher;
import org.unlogged.springwebfluxdemo.model.Person;
import reactor.core.publisher.Mono;

public class PersonAgeEnricherV1 implements EnricherV1<Person> {
    @Override
    public Person enrich(Person obj) {
        System.out.println("L2 Av1 call : " + obj);
        obj.setAge(obj.getAge() + 1);
        return obj;
    }
}

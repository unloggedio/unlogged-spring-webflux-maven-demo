package org.unlogged.springwebfluxdemo.enrich.nonreactive;

import org.unlogged.springwebfluxdemo.enrich.Enricher;
import org.unlogged.springwebfluxdemo.model.Person;

import java.util.List;

public class CompositeEnricherV1 implements EnricherV1<Person> {

    private final List<EnricherV1> enrichers;

    public CompositeEnricherV1(List<EnricherV1> enrichers) {
        this.enrichers = enrichers;
    }

    @Override
    public Person enrich(Person obj) {
        System.out.println("L1 v1 outer");
        for (EnricherV1 ev1 : enrichers) {
            System.out.println("L1 v1 call");
            obj = (Person) ev1.enrich(obj);
        }
        return obj;
    }
}

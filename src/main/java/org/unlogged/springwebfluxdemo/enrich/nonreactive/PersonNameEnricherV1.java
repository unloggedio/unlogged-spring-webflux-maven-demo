package org.unlogged.springwebfluxdemo.enrich.nonreactive;

import org.unlogged.springwebfluxdemo.enrich.Enricher;
import org.unlogged.springwebfluxdemo.model.Person;
import reactor.core.publisher.Mono;

public class PersonNameEnricherV1 implements EnricherV1<Person> {

    @Override
    public Person enrich(Person obj) {
        System.out.println("L2 Nv1 call : " + obj);
        obj.setName(obj.getName() + "#En");
        return obj;
    }
}

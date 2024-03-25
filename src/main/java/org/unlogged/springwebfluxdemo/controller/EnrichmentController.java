package org.unlogged.springwebfluxdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unlogged.springwebfluxdemo.enrich.CompositeEnricher;
import org.unlogged.springwebfluxdemo.enrich.Enricher;
import org.unlogged.springwebfluxdemo.enrich.PersonAgeEnricher;
import org.unlogged.springwebfluxdemo.enrich.PersonNameEnricher;
import org.unlogged.springwebfluxdemo.model.Person;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/enrich")
public class EnrichmentController {

    @RequestMapping("/person/v1")
    public Mono<Person> enrichDefaultPerson() {
        Person def = new Person("p1", 1);
        List<Enricher> enricherList = new ArrayList<>();
        PersonNameEnricher nameEnricher = new PersonNameEnricher();
        PersonAgeEnricher ageEnricher = new PersonAgeEnricher();
        enricherList.add(nameEnricher);
        enricherList.add(ageEnricher);
        CompositeEnricher compositeEnricher = new CompositeEnricher(enricherList);
        return compositeEnricher.enrich(def);
    }

    @RequestMapping("/person/v2")
    public Mono<Person> enrichDefaultPersonReactive() {
        return Mono.just(new Person("p2", 1))
                .flatMap(person -> new CompositeEnricher(
                        Arrays.asList(
                                new PersonNameEnricher(), new PersonAgeEnricher()))
                        .enrich(person));
    }
}

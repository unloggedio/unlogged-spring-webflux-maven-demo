package org.unlogged.springwebfluxdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.unlogged.springwebfluxdemo.enrich.CompositeEnricher;
import org.unlogged.springwebfluxdemo.enrich.Enricher;
import org.unlogged.springwebfluxdemo.enrich.PersonAgeEnricher;
import org.unlogged.springwebfluxdemo.enrich.PersonNameEnricher;
import org.unlogged.springwebfluxdemo.enrich.nonreactive.CompositeEnricherV1;
import org.unlogged.springwebfluxdemo.enrich.nonreactive.EnricherV1;
import org.unlogged.springwebfluxdemo.enrich.nonreactive.PersonAgeEnricherV1;
import org.unlogged.springwebfluxdemo.enrich.nonreactive.PersonNameEnricherV1;
import org.unlogged.springwebfluxdemo.model.Person;
import reactor.core.publisher.Flux;
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


    @RequestMapping("/person/v0")
    public Person EnrichNonReactive() {
        Person def = new Person("p1", 1);
        List<EnricherV1> enricherList = new ArrayList<>();
        PersonNameEnricherV1 nameEnricher = new PersonNameEnricherV1();
        PersonAgeEnricherV1 ageEnricher = new PersonAgeEnricherV1();
        enricherList.add(nameEnricher);
        enricherList.add(ageEnricher);
        CompositeEnricherV1 compositeEnricher = new CompositeEnricherV1(enricherList);
        return compositeEnricher.enrich(def);
    }

    @RequestMapping("/simple/m")
    public Mono<String> simpleAppend(@RequestParam String simple) {
        return Mono.just(simple).map(simpleString -> {
            System.out.println("In Map ---------------------||");
            return simpleString + "#ENG";
        });
    }

    @RequestMapping("/simple/f")
    public Mono<Object> simpleAppendFlat(@RequestParam String simple) {
        return Mono.just(simple).flatMap(simpleString -> {
            System.out.println("In FlatMap ---------------------||");
            return Mono.just("E");
        });
    }
    //more complex operations and pojos

    @RequestMapping("/person/parallel")
    public Flux<Person> enrichPersonsInParallel() {
        List<Person> persons = Arrays.asList(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 28)
        );

        return Flux.fromIterable(persons)
                .parallel()
                .flatMap(person -> Mono.just(person)
                        .flatMap(p -> new CompositeEnricher(
                                Arrays.asList(new PersonNameEnricher(), new PersonAgeEnricher()))
                                .enrich(p)))
                .sequential(); // Merge back to sequential Flux
    }
}

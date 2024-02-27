package org.unlogged.springwebfluxdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.unlogged.springwebfluxdemo.model.Person;
import org.unlogged.springwebfluxdemo.repository.PersonReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@RestController
public class MongoReactiveOpsController {
    @Autowired
    PersonReactiveMongoRepository mongoRepository;

    public Mono<Person> addPerson() {
        Mono<Person> personMono
                = mongoRepository.save(new Person("personY", 63));
        return personMono;
    }

    public Flux<Person> findPeopleByName(String name) {
        return mongoRepository.findPeopleByName(name);
    }

    public Flux<Person> findPeopleByAge(Integer age) {
        return mongoRepository.findPeopleByAge(age);
    }

    public Flux<Person> findAll() {
        Flux<Person> personFlux = mongoRepository
                .findAll();
        return personFlux;
    }

    public Mono<Long> countAll() {
        return mongoRepository.findAll().count();
    }

    //behaves like aps, no proper response body
    public Mono<ResponseEntity<String>> deleteAllByName(String name) {
        return mongoRepository.deleteByName(name).
                map(element -> {
                    return ResponseEntity.ok("Done");
                });
    }
}

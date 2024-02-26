package org.unlogged.springwebfluxdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.unlogged.springwebfluxdemo.model.Person;
import org.unlogged.springwebfluxdemo.repository.PersonReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class MongoReactiveOpsController {
    @Autowired
    PersonReactiveMongoRepository mongoRepository;

    public Mono<Person> addPerson() {
        Mono<Person> personMono
                = mongoRepository.save(new Person("personX", 64));
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
}

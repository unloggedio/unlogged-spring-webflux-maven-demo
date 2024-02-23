//package org.unlogged.springwebfluxdemo.repository;
//
//import org.springframework.data.repository.reactive.ReactiveCrudRepository;
//import org.springframework.stereotype.Repository;
//import org.unlogged.springwebfluxdemo.model.Person;
//import reactor.core.publisher.Flux;
//
//@Repository
//public interface PersonReactiveCrudRepository
//        extends ReactiveCrudRepository<Person, String> {
//
//    Flux<Person> findAllByValue(String value);
//}
package org.unlogged.springwebfluxdemo.repository;

import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import org.unlogged.springwebfluxdemo.model.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PersonReactiveMongoRepository
        extends ReactiveMongoRepository<Person, String> {

    @Query("{ 'name' : ?0 }")
    Flux<Person> findPeopleByName(String name);

    @Query("{ 'age' : ?0 }")
    Flux<Person> findPeopleByAge(Integer age);

    @Query("{'age' : {'$lt' : ?0}}")
    Flux<Person> findPeopleWithAgeLessThan(Integer age);

    @Query("{'age' : {'$gt' : ?0}}")
    Flux<Person> findPeopleWithAgeGreaterThan(Integer age);

    @DeleteQuery("{ 'name' : ?0 }")
    Mono<Void> deleteByName(String name);
}
//package org.unlogged.springwebfluxdemo.repository;
//
//import org.springframework.data.repository.reactive.ReactiveCrudRepository;
//import org.springframework.stereotype.Repository;
//import org.unlogged.springwebfluxdemo.model.Account;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@Repository
//public interface AccountCrudRepository
//        extends ReactiveCrudRepository<Account, String> {
//
//    Flux<Account> findAllByValue(String value);
//
//    Mono<Account> findFirstByOwner(Mono<String> owner);
//}
//package org.unlogged.springwebfluxdemo.repository;
//
//import io.reactivex.rxjava3.core.Observable;
//import io.reactivex.rxjava3.core.Single;
//import org.springframework.data.repository.reactive.RxJava2CrudRepository;
//import org.springframework.stereotype.Repository;
//import org.unlogged.springwebfluxdemo.model.Account;
//
//@Repository
//public interface AccountRxJavaRepository
//  extends RxJava2CrudRepository<Account, String> {
//
//    Observable<Account> findAllByValue(Double value);
//    Single<Account> findFirstByOwner(Single<String> owner);
//}
package org.unlogged.springwebfluxdemo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import org.unlogged.springwebfluxdemo.entity.User;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
}

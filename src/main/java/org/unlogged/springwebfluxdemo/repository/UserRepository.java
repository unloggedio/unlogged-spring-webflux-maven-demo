package org.unlogged.springwebfluxdemo.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import org.unlogged.springwebfluxdemo.entity.User;

@Repository
public interface UserRepository extends ReactiveCrudRepository<User, String> {
}

package org.unlogged.springwebfluxdemo.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import org.unlogged.springwebfluxdemo.model.Player;

@Repository
public interface PlayerRepository extends ReactiveCrudRepository<Player, Long> {
}
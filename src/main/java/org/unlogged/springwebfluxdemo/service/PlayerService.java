package org.unlogged.springwebfluxdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unlogged.springwebfluxdemo.model.Player;
import org.unlogged.springwebfluxdemo.repository.PlayerRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlayerService {


    private final PlayerRepository repository;

    @Autowired
    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public Mono<Player> createPlayer(Player player) {
        player.setId(null);
        return repository.save(player);
    }

    public Flux<Player> getAllPlayers() {
        return repository.findAll();
    }

    public Mono<Player> getPlayerById(Long id) {
        return repository.findById(id);
    }

    public Mono<Player> updatePlayer(Player player) {
        return repository.save(player); // Update based on existing ID
    }

    public Mono<Void> deletePlayerById(Long id) {
        return repository.deleteById(id);
    }
}

package org.unlogged.springwebfluxdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.unlogged.springwebfluxdemo.model.Player;
import org.unlogged.springwebfluxdemo.service.PlayerService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public Mono<Player> createPlayer(@RequestBody Player player) {
        return playerService.createPlayer(player);
    }

    @PostMapping("/batch")
    @ResponseStatus(HttpStatus.CREATED)
    public Flux<Player> batchInsertProducts(@RequestBody List<Player> players) {
        players.forEach(player -> player.setId(null));
        return playerService.saveAll(players);
    }

    @GetMapping
    public Flux<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @GetMapping("/{id}")
    public Mono<Player> getPlayerById(@PathVariable Long id) {
        return playerService.getPlayerById(id);
    }

    @PutMapping("/{id}")
    public Mono<Player> updatePlayer(@PathVariable Long id, @RequestBody Player player) {
        player.setId(id); // Ensure ID is set for update
        return playerService.updatePlayer(player);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deletePlayerById(@PathVariable Long id) {
        return playerService.deletePlayerById(id);
    }
}

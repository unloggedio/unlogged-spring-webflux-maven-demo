package org.unlogged.springwebfluxdemo.service;

import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.unlogged.springwebfluxdemo.model.Player;
import org.unlogged.springwebfluxdemo.model.Product;
import org.unlogged.springwebfluxdemo.repository.PlayerRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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

    //To check functioning of Transactional. It will throw an exception and would not insert if name is empty
    /**
     * Dummy Input to test This
     * [
     *   {
     *     "id": 1,
     *     "name": "John Doe",
     *     "age": 25
     *   },
     *   {
     *     "id": 2,
     *     "name": "Jane Smith",
     *     "age": 30
     *   },
     *   {
     *     "id": 4,
     *     "name": "",
     *     "age": 28
     *   }
     * ]
     * */
    @Transactional
    public Flux<Player> saveAll(List<Player> players) {
        players.forEach(player -> player.setId(null));
        return repository.saveAll(players)
                .doOnNext(this::throwStatusExceptionWhenNameIsEmpty);
    }
    private void throwStatusExceptionWhenNameIsEmpty(Player player) {
        if(StringUtil.isNullOrEmpty(player.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid name");
        }
    }
}

package org.unlogged.springwebfluxdemo.webclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.unlogged.springwebfluxdemo.model.dto.UserDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/client/users")
public class UserWebClientController {

    private final UserClient userWebClient;

    @Autowired
    public UserWebClientController(UserClient userWebClient) {
        this.userWebClient = userWebClient;
    }

    @GetMapping
    public Flux<UserDto> getAllUsers() {
        return userWebClient.getAllUsers();
    }

    @GetMapping("/{id}")
    public Mono<UserDto> getUserById(@PathVariable String id) {
        return userWebClient.getUserById(id);
    }

    @PostMapping
    public Mono<UserDto> createUser(@RequestBody UserDto userDto) {
        return userWebClient.createUser(userDto);
    }

    @PutMapping("/{id}")
    public Mono<UserDto> updateUser(@PathVariable String id, @RequestBody UserDto userDto) {
        return userWebClient.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable String id) {
        return userWebClient.deleteUser(id);
    }
}


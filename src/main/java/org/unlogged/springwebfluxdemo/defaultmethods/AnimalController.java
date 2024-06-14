package org.unlogged.springwebfluxdemo.defaultmethods;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class AnimalController implements Animal{

    @GetMapping("/animal/mono")
    public Mono<ResponseEntity<String>> getAnimalMono(String userId) {
        // Directly call the default method from the Animal interface
        return getDefaultMethodMono(userId);
    }

    @GetMapping("/animal/flux")
    public Flux<String> getAnimalFlux() {
        // Directly call the default method from the Animal interface
        return getDefaultMethodFlux();
    }

    @GetMapping("/animal/static")
    public Mono<String> getStaticMethod() {
        // Call the static method from the Animal interface
        return Animal.getStaticMethod();
    }

}

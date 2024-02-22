package org.unlogged.springwebfluxdemo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@CrossOrigin(value = {"http://allowed-origin.com"},
        allowedHeaders = {"unlogged-Allowed"},
        maxAge = 900
)
@RestController
public class CorsOnClassController {

    @PutMapping("/cors-enabled-endpoint")
    public Mono<String> corsEnabledEndpoint() {
        return Mono.just("Cors-Enabled");
    }

    @CrossOrigin({"http://another-allowed-origin.com"})
    @PutMapping("/endpoint-with-extra-origin-allowed")
    public Mono<String> corsEnabledWithExtraAllowedOrigin() {
        return Mono.just("Cors-Enabled-with-ExtractAllowedOrigin");
    }

}
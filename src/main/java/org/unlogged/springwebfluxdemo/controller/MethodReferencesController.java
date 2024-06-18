package org.unlogged.springwebfluxdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unlogged.springwebfluxdemo.service.MethodRefService;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/method-references")
public class MethodReferencesController {

    private final MethodRefService service;

    @Autowired
    public MethodReferencesController(MethodRefService service) {
        this.service = service;
    }

    @GetMapping("/static-method")
    public Flux<String> useStaticMethodReference() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        // Using reference to a static method
        return Flux.fromIterable(names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList()));
    }

    @GetMapping("/instance-method")
    public Flux<String> useInstanceMethodReference() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        // Using reference to an instance method of an existing object
        return Flux.fromIterable(names.stream()
                .map(service::toLowerCase)
                .collect(Collectors.toList()));
    }

    @GetMapping("/arbitrary-instance-method")
    public Flux<Integer> useArbitraryInstanceMethodReference() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        // Using reference to an instance method of an arbitrary object of a particular type
        return Flux.fromIterable(names.stream()
                .map(String::length)
                .collect(Collectors.toList()));
    }

    @GetMapping("/constructor")
    public Flux<MyObject> useConstructorReference() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        // Using reference to a constructor
        return Flux.fromIterable(names.stream()
                .map(MyObject::new)
                .collect(Collectors.toList()));
    }

    static class MyObject {
        private final String name;

        public MyObject(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}


package org.unlogged.springwebfluxdemo.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.unlogged.springwebfluxdemo.client.GreetingClient;
import org.unlogged.springwebfluxdemo.model.GenericTypeWrapper;
import org.unlogged.springwebfluxdemo.model.TypeWrapper;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class EntryPointController {

    @Autowired
    private GreetingClient greetingClient;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.setAllowedFields("inputdata", "data");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping("/hello")
    public Mono<String> getGreeting() {
        return greetingClient.getMessage();
    }

    @RequestMapping("/e")
    public Mono<String> getE() {
        return greetingClient.getE();
    }

    @RequestMapping("/args1")
    public ResponseEntity<Mono<String>> getArgs1() {
        return ResponseEntity.ok(greetingClient.getMessage());
    }

    @RequestMapping("/typeWrapped")
    public ResponseEntity<Mono<TypeWrapper>> getTypeWrapped() {
        return ResponseEntity.ok(greetingClient.getTypeWrappedObject());
    }

    @RequestMapping(path = "/typeWrapped/{id}")
    public ResponseEntity<Mono<TypeWrapper>> getTypeWrappedPv(@PathVariable String id) {
        return ResponseEntity.ok(greetingClient.getTypeWrappedObjectPV(id));
    }

    @RequestMapping("/typeWrapped/blocked")
    public TypeWrapper getTypeWrappedBlocked() {
        return greetingClient.getTypeWrappedObject().block();
    }

    @RequestMapping("/typeWrapped/v2")
    public Mono<ResponseEntity<TypeWrapper>> getMonoRespTypeEntity() {
        return greetingClient.getTypeWrappedObject().map(ResponseEntity::ok);
    }

    @RequestMapping("/monoWrapped/i1")
    public Mono<ResponseEntity<String>> getMonoStringText() {
        return Mono.just(ResponseEntity.ok("SomeString"));
    }

    @RequestMapping("/someBean")
    public ResponseEntity<Mono<Bean>> getRandomBean() {
        return ResponseEntity.ok(greetingClient.getRandomBean());
    }

    @RequestMapping("/listOfStrings")
    public ResponseEntity<Mono<List<String>>> getStingList() {
        return ResponseEntity.ok(greetingClient.getListofMonoStrings());
    }

    @RequestMapping("/args2")
    public Mono<String> getArgs2(@RequestParam String stringInput) {
        return greetingClient.getE();
    }

    @RequestMapping("/genericTypeWrapped")
    public ResponseEntity<Mono<GenericTypeWrapper<String>>> getGenericTypeWrapped() {
        return ResponseEntity.ok(greetingClient.getGenericTypeWrapper());
    }
}

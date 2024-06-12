package org.unlogged.springwebfluxdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/var")
public class VarKeywordController {

    @RequestMapping("/int")
    public Mono<Integer> getIntVar(@RequestParam int n) {
        var a = 9;
        a+=n;
        return Mono.just(a);
    }
//Issue with float
    @RequestMapping("/float")
    public Mono<Float> getFloatVar(@RequestParam float n) {
        var a = 9.0f;
        a+=n;
        return Mono.just(a);
    }
//Issue with double
    @RequestMapping("/double")
    public Mono<Double> getDoubleVar(@RequestParam double n) {
        var a = 9.99;
        a+=n;
        return Mono.just(a);
    }

    @RequestMapping("/String")
    public Mono<String> getStringVar(@RequestParam char n) {
        var a = 'c';
        var b= "ab";
        return Mono.just(b+a+n);
    }

    @RequestMapping("/char")
    public Mono<Character> getCharVar(@RequestParam char n) {
        var c = n;
        return Mono.just(c);
    }

    //Integer array
    @RequestMapping("/array")
    public Flux<Integer> getIntegerArrayVar(@RequestParam int n) {
        var array = new Integer[n];
        for (var i = 0; i < n; i++) {
            array[i] = i + 1;
        }
        return Flux.fromArray(array);
    }

    // Object
    @RequestMapping("/object")
    public Mono<ObjectTest> getObjectVar(@RequestParam int n) {
        var obj = new ObjectTest(n, 'a', "test", 9.99);
        return Mono.just(obj);
    }

    // array of objects
    @RequestMapping("/objarray")
    public Flux<ObjectTest> getObjectArrayVar(@RequestParam int n) {
        var array = new ObjectTest[n];
        for (var i = 0; i < n; i++) {
            array[i] = new ObjectTest(i+1,'a', "test", 9.99);
        }
        return Flux.fromArray(array);
    }
}

class ObjectTest {
    int a;
    char b;
    String c;
    Double d;

    public ObjectTest(int a, char b, String c, Double d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
}

package org.unlogged.springwebfluxdemo.controller;

import io.micrometer.observation.ObservationRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.unlogged.springwebfluxdemo.client.GreetingClient;
import org.unlogged.springwebfluxdemo.exception.WebFluxException;
import org.unlogged.springwebfluxdemo.exception.WebfluxError;
import org.unlogged.springwebfluxdemo.model.TypeWrapper;
import reactor.core.observability.micrometer.Micrometer;
import reactor.core.publisher.Mono;

import java.util.Observable;

@RestController
@RequestMapping("/mono")
public class MonoOpsController {

    @Autowired
    private GreetingClient greetingClient;
    private MockUtils mockUtils = new MockUtils();

    private ObservationRegistry observationRegistry = ObservationRegistry.create();

    @RequestMapping("/ser/typewrapper/1")
    public Mono<ResponseEntity<TypeWrapper>> getMonoRespTypeEntity() {
        return greetingClient.getTypeWrappedObject().map(ResponseEntity::ok);
    }

    @RequestMapping("/ser/string/1")
    public Mono<String> getMonostring() {
        return mockUtils.returnMockString();
    }

    @RequestMapping("/ser/typewrapper/2")
    public Mono<ResponseEntity<Mono<TypeWrapper>>> getMr2() {
        return Mono.just(ResponseEntity.ok(greetingClient.getTypeWrappedObject()));
    }

    @RequestMapping("/ser/block/1")
    public TypeWrapper blockedGet1() {
        Mono<TypeWrapper> typeWrapperMono = greetingClient.getTypeWrappedObject();
        System.out.println("Type Wrapped : " + typeWrapperMono.toString());
        return typeWrapperMono.block();
    }

    @RequestMapping("/block/1")
    public String getBlockedString() {
        var testString = "MonoString";
        Mono<String> monoString = Mono.just(testString);
        return monoString.block();
    }

    @RequestMapping("/tap/1")
    public Mono<TypeWrapper> getMr3() {
        return greetingClient.getTypeWrappedObject()
                .name("Some name")
                .tap(Micrometer.observation(observationRegistry));
    }

    @RequestMapping("/mono/error")
    public Mono<String> returnMonoErrorBasic() {
        if (true) {
            return Mono.error(new RuntimeException("Custom throw"));
        }
        return Mono.just("Exception");
    }

    @RequestMapping("/mono/error/custom")
    public Mono<String> returnCustomExceptionFromMono() {
        if (true) {
            return Mono.error(new WebFluxException(WebfluxError.CUSTOM_THROW,
                    new RuntimeException("ThrowCustomException")));
        }
        return Mono.just("Exception");
    }

    @RequestMapping("/multiline")
    public Mono<String> getMultilineString() {
        String multilineString = """
                This is a multiline string.
                It is being used to test jdk21
                """;
        return Mono.just(multilineString);
    }
//
//    It is a java preview feature
//    @RequestMapping("/string-template")
//    public Mono<String> getStringTemplate() {
//        int a = 5;
//        int b = 8;
//        String result = "\{a} + \{b} = \{a+b}";
//        return Mono.just(result);
//    }

    @RequestMapping("/enhanced/switch/1")
    public Mono<Integer> calculate(@RequestParam String operation, @RequestParam int a, @RequestParam int b) {
        Mono<Integer> answer = switch (operation) {
//            case String s when s == "+" -> Mono.just(a + b);
            case "-" -> Mono.just(a - b);
//            case null -> Mono.just(0);
            default -> Mono.just(a * b);
        };
        return answer;
    }

//    Currently this will fail with Unlogged.

//    public Mono<Integer> switchJDK21(Object operation, int a, int b) {
//        Mono<Integer> answer = switch (operation) {
//            case String s when s == "+" -> Mono.just(a + b);
//            case String s when s == "-" -> Mono.just(a - b);
//            case Integer i -> Mono.just(i + i);
//            case null -> Mono.just(0);
//            default -> Mono.just(a * b);
//        };
//        return answer;
//
//    }

    public Mono<Integer> calculateYield(String operation, int a, int b) {
        Mono<Integer> answer = switch (operation) {
//            case String s when s == "+" : yield Mono.just(a + b);
            case "-":
                yield Mono.just(a - b);
//            case null : yield Mono.just(0);
            default:
                yield Mono.just(a * b);
        };
        return answer;

    }

}

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

    @RequestMapping("/ser/nested-mono/1")
    public Mono<Mono<String>> getNestedMonoString() {
        return mockUtils.returnNestedMockStringMono();
    }

    @RequestMapping("/ser/combined-strings/1")
    public Mono<String> getCombinedExtractedString() {
        Mono<String> mockStringMono = mockUtils.returnMockString();
        Mono<Mono<String>> nestedMockStringMono = mockUtils.returnNestedMockStringMono();
        return nestedMockStringMono
                .flatMap(nestedMono -> nestedMono.zipWith(mockStringMono,
                        (nestedString, mockString) -> mockString + " - " + nestedString))
                .flatMap(Mono::just);
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

    @RequestMapping("/enhanced/switch/1")
    public Mono<Integer> calculate(@RequestParam String operation, @RequestParam int a, @RequestParam int b) {
        Mono<Integer> answer = switch (operation) {
//            case String s when s == "+" -> Mono.just(a + b);
            case "-" -> Mono.just(a - b);
            default -> Mono.just(a * b);
        };
        return answer;
    }

    public Mono<Integer> calculateYield(String operation, int a, int b) {
        Mono<Integer> answer = switch (operation) {
//            case String s when s == "+" : yield Mono.just(a + b);
            case "-":
                yield Mono.just(a - b);
            default:
                yield Mono.just(a * b);
        };
        return answer;

    }

}

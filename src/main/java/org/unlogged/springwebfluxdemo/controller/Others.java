package org.unlogged.springwebfluxdemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.types.dsl.ListPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.unlogged.springwebfluxdemo.exception.WebfluxError;
import org.unlogged.springwebfluxdemo.model.GenericTypeWrapper;
import org.unlogged.springwebfluxdemo.model.Person;
import org.unlogged.springwebfluxdemo.model.circle.CircleChild;
import org.unlogged.springwebfluxdemo.model.circle.CircleParent;
import org.unlogged.springwebfluxdemo.service.flow1.CustomService;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;

import javax.imageio.ImageIO;
//import javax.validation.constraints.Null;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.web.reactive.function.server.RequestPredicates.all;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@RestController
@RequestMapping("/others")
public class Others {

    @Autowired
    private CustomService customService;
    private static final Set<String> ALLOWED_CONTENT_TYPES =
            Set.of(MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE);

    private Boolean checkImageValidity(DataBuffer dataBuffer, MediaType contentType) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(dataBuffer.asInputStream());
        dataBuffer.readPosition(0);
        if (bufferedImage == null) {
            if (ALLOWED_CONTENT_TYPES.contains(contentType.toString())) {
                return false;
            }
        }
        return true;
    }

    public Mono<String> getStingO1(boolean capitalize, Optional<WebfluxError> webfluxError) {
        return Mono.just(webfluxError)
                .map(optional ->
                {
                    String returnVal = "";
                    if (optional.isEmpty()) {
                        returnVal = "Empty";
                    } else {
                        returnVal = webfluxError.get().getMessage();
                    }

                    if (capitalize) {
                        returnVal = returnVal.toUpperCase();
                    }
                    return returnVal;
                });
    }

    public Mono<String> getString02MultiValueMap(MultiValueMap<String, String> params) {
        return Mono.just(params.getFirst("string"));
    }

    public Mono<Long> getNext(Class<? extends Object> someClass, String e) {
        return Mono.just(e).map(elem -> {
            return (long) (e + "#" + someClass.toString()).hashCode();
        });
    }

    public ListPath<String, StringPath> getListPath0() {
        ListPath<String, StringPath> listPath = null;
        return listPath;
    }

    public StringPath getStringPath0(String input) {
        StringPath sp = null;
        return sp;
    }

    public <T> Consumer<Signal<T>> reg(Consumer<T> log) {
        return Signal -> {
            return;
        };
    }

    public CustomService getCustomService() {
        return this.customService;
    }

    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    public Criteria getCriteria() {
        return Criteria.byExample(Example.of(new Person("personY", 63)));
    }

    //should serialize and not throw a failed to serialize Exception
    public Criteria getCriteria1(String name) {
        return where("Person.name").is(name);
    }

    public Query getQuery() {
        return query(getCriteria());
    }

    public Mono<CircleParent> getCircleParent() {
        return Mono.just(new CircleParent(1));
    }
}

package org.unlogged.springwebfluxdemo.integrationpatterns.scattergather.externalservices.bluedart;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("integration/bluedart/")
public class MockBlueDartController {

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<BlueDartResponse> getServiceOptions(@RequestBody BlueDartRequest request) {
        List<BlueDartResponse> responses = Arrays.asList(
                createResponse(request.getFrom(), request.getTo(), 12.0),
                createResponse(request.getFrom(), request.getTo(), 18.0)
        );
        return Flux.fromIterable(responses);
    }

    private BlueDartResponse createResponse(String from, String to, Double price) {
        BlueDartResponse response = new BlueDartResponse();
        response.setFrom(from);
        response.setTo(to);
        response.setPrice(price);
        response.setDate(LocalDate.now());
        response.setServiceProvider("BlueDart");
        return response;
    }

    private static class BlueDartRequest {
        private String from;
        private String to;

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }
    }
}

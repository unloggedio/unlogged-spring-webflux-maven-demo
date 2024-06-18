package org.unlogged.springwebfluxdemo.integrationpatterns.scattergather.externalservices.dhl;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("integration/dhl")
public class MockDHLController {

    @GetMapping(value = "{from}/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<DHLResponse> getServiceOptions(@PathVariable String from, @PathVariable String to) {
        List<DHLResponse> responses = responseList(from,to);
        return Flux.fromIterable(responses);
    }

    private List<DHLResponse> responseList(String from, String to) {
        if(from.equals("dehradun") && to.equals("delhi")) {
            List<DHLResponse> responses = Arrays.asList(
                    new DHLResponse(101.11, LocalDate.now()),
                    new DHLResponse(101.11, LocalDate.now())
            );
            return responses;
        } else return new ArrayList<DHLResponse>();
    }
}

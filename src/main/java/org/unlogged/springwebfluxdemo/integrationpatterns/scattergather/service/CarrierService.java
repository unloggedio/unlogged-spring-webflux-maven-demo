package org.unlogged.springwebfluxdemo.integrationpatterns.scattergather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unlogged.springwebfluxdemo.integrationpatterns.scattergather.client.BlueDartClient;
import org.unlogged.springwebfluxdemo.integrationpatterns.scattergather.client.DHLClient;
import org.unlogged.springwebfluxdemo.integrationpatterns.scattergather.dto.CarrierResponse;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Service
public class CarrierService {

    @Autowired
    private DHLClient dhlClient;

    @Autowired
    private BlueDartClient blueDartClient;

    public Flux<CarrierResponse> getCarriers(String from, String to){
        return Flux.merge(
                        this.dhlClient.getServiceOptions(from, to),
                        this.blueDartClient.getServiceOptions(from, to)
                )
                .take(Duration.ofSeconds(3));
    }
}
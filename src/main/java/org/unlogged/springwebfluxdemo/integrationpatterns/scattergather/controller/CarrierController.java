package org.unlogged.springwebfluxdemo.integrationpatterns.scattergather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unlogged.springwebfluxdemo.integrationpatterns.scattergather.dto.CarrierResponse;
import org.unlogged.springwebfluxdemo.integrationpatterns.scattergather.service.CarrierService;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("integration")
public class CarrierController {

    @Autowired
    private CarrierService service;

    @GetMapping(value = "carrierV1/{from}/{to}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<CarrierResponse> getCarriers(@PathVariable String from, @PathVariable String to){
        return this.service.getCarriers(from, to);
    }
}

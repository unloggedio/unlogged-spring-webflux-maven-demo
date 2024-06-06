package org.unlogged.springwebfluxdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unlogged.springwebfluxdemo.component.VehicleComponent;
import org.unlogged.springwebfluxdemo.sealedKeywordUsage.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/vehicles")
public class SealedClassController {

    @Autowired
    private final VehicleComponent vehicleComponent;

    public SealedClassController(VehicleComponent vehicleComponent) {
        this.vehicleComponent = vehicleComponent;
    }

    @RequestMapping("/getAll")
    public Flux<Vehicle> getAllVehicles() {
        return vehicleComponent.getVehicles();
    }
}

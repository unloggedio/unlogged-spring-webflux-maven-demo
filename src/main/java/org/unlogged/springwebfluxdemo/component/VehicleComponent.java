package org.unlogged.springwebfluxdemo.component;

import org.springframework.stereotype.Component;
import org.unlogged.springwebfluxdemo.sealedKeywordUsage.*;

import java.util.ArrayList;
import java.util.List;

import reactor.core.publisher.Flux;

/**
 * Mimicking a Database
 */

@Component
public class VehicleComponent {

    private Flux<Vehicle> vehicles;

    public VehicleComponent() {
        initializeVehicles();
    }

    private void initializeVehicles() {
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Car("Honda City", 4));
        vehicleList.add(new Truck("Eicher Pro", 400));
        vehicleList.add(new ElectricCar("Tata Nexon EV", 4, 350));
        vehicleList.add(new DieselTruck("Mahindra Jeeto", 550, 500));

        vehicles = Flux.fromIterable(vehicleList);
    }

    public Flux<Vehicle> getVehicles() {
        return vehicles;
    }
}

package org.unlogged.springwebfluxdemo.sealedKeywordUsage;

public sealed class Vehicle implements VehicleType permits Car, Truck {
    private String model;

    public Vehicle(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public String getInfo() {
        return "Vehicle Model: " + model;
    }

    @Override
    public String getType() {
        return "General Vehicle";
    }
}
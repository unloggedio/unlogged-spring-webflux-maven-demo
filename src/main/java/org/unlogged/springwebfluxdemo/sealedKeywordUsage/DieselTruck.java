package org.unlogged.springwebfluxdemo.sealedKeywordUsage;

public final class DieselTruck extends Truck {
    private int fuelCapacity;
    public DieselTruck(String model, int capacity, int fuelCapacity) {
        super(model, capacity);
        this.fuelCapacity = fuelCapacity;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "\nFuel Type: Diesel\nFuel Capacity (liters): " + fuelCapacity;
    }
}

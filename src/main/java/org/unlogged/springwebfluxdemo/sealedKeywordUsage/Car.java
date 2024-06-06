package org.unlogged.springwebfluxdemo.sealedKeywordUsage;

public sealed class Car extends Vehicle implements ExtendedVehicleType  permits ElectricCar {
    private int doors;

    public Car(String model, int doors) {
        super(model);
        this.doors = doors;
    }

    public int getDoors() {
        return doors;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "\nType: Car\nNumber of doors: " + doors;
    }

    @Override
    public String getType() {
        return "Car";
    }

    @Override
    public String getExtendedType() {
        return "Extended Car Type";
    }
}
package org.unlogged.springwebfluxdemo.sealedKeywordUsage;

public sealed class Car extends Vehicle permits ElectricCar {
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
}
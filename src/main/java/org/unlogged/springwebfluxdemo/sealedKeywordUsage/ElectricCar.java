package org.unlogged.springwebfluxdemo.sealedKeywordUsage;

//non-sealed public class C extends B {
//}

public non-sealed class ElectricCar extends Car {
    private int batteryCapacity;

    public ElectricCar(String model, int doors, int batteryCapacity) {
        super(model, doors);
        this.batteryCapacity = batteryCapacity;
    }

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "\nFuel Type: Electric\nBattery Capacity (kWh): " + batteryCapacity;
    }
}


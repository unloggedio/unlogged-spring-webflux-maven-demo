package org.unlogged.springwebfluxdemo.sealedKeywordUsage;

public sealed class Truck extends Vehicle implements ExtendedVehicleType permits DieselTruck {
    private int capacity;

    public Truck(String model, int capacity) {
        super(model);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + "\nType: Truck\nCapacity (in tons): " + capacity;
    }

    @Override
    public String getType() {
        return "Truck";
    }

    @Override
    public String getExtendedType() {
        return "Extended Truck Type";
    }
}

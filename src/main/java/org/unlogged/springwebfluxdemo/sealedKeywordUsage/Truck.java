package org.unlogged.springwebfluxdemo.sealedKeywordUsage;
//
//final public class D extends B{
//}

public sealed class Truck extends Vehicle permits DieselTruck {
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
}

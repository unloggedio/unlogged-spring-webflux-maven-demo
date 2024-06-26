package org.unlogged.springwebfluxdemo.model.weather;


public class WeatherInfo {
    public Location location;
    public Current current;

    public WeatherInfo(Location location, Current current) {
        this.location = location;
        this.current = current;
    }

    public WeatherInfo() {
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "location=" + location +
                ", current=" + current +
                '}';
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }
}


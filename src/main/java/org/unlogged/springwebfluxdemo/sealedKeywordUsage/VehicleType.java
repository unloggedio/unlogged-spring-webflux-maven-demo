package org.unlogged.springwebfluxdemo.sealedKeywordUsage;

public sealed interface VehicleType permits Vehicle, ExtendedVehicleType {
    String getType();
}
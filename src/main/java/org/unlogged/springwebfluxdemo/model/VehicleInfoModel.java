package org.unlogged.springwebfluxdemo.model;

public class VehicleInfoModel {
    private String model;
    private String type;
    private String extendedType;

    public VehicleInfoModel(String model, String type, String extendedType) {
        this.model = model;
        this.type = type;
        this.extendedType = extendedType;
    }

    // getters and setters
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExtendedType() {
        return extendedType;
    }

    public void setExtendedType(String extendedType) {
        this.extendedType = extendedType;
    }
}
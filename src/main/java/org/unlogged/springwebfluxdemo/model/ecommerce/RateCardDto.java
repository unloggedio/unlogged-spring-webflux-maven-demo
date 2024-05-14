package org.unlogged.springwebfluxdemo.model.ecommerce;

public class RateCardDto {
    String id;
    String name;
    String percentCharge;

    public RateCardDto(String id, String name, String percentCharge) {
        this.id = id;
        this.name = name;
        this.percentCharge = percentCharge;
    }

    public String getPercentCharge() {
        return percentCharge;
    }

    public void setPercentCharge(String percentCharge) {
        this.percentCharge = percentCharge;
    }

    public RateCardDto() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RateCardDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", percentCharge='" + percentCharge + '\'' +
                '}';
    }
}

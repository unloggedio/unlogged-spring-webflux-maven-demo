package org.unlogged.springwebfluxdemo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ratecards")
public class RateCard {

    @Id
    String id;
    String name;
    String percentCharge;

    public RateCard(String id, String name, String percentCharge) {
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

    public RateCard() {}

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
}

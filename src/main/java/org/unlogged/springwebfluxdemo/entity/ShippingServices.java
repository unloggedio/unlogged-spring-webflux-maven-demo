package org.unlogged.springwebfluxdemo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "shippingservices")
public class ShippingServices {

    @Id
    private String id;
    private String serviceProvider;
    private List<String> operationPinCodes;
    private double shippingCostPerItem;
    private long maxCapacity;
    private List<RateCard> rateCards;

    public ShippingServices(String id, String serviceProvider, List<String> operationPinCodes, double shippingCostPerItem, long maxCapacity, List<RateCard> rateCards) {
        this.id = id;
        this.serviceProvider = serviceProvider;
        this.operationPinCodes = operationPinCodes;
        this.shippingCostPerItem = shippingCostPerItem;
        this.maxCapacity = maxCapacity;
        this.rateCards = rateCards;
    }

    public ShippingServices() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(String serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public List<String> getOperationPinCodes() {
        return operationPinCodes;
    }

    public void setOperationPinCodes(List<String> operationPinCodes) {
        this.operationPinCodes = operationPinCodes;
    }

    public double getShippingCostPerItem() {
        return shippingCostPerItem;
    }

    public void setShippingCostPerItem(double shippingCostPerItem) {
        this.shippingCostPerItem = shippingCostPerItem;
    }

    public long getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(long maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public List<RateCard> getRateCards() {
        return rateCards;
    }

    public void setRateCards(List<RateCard> rateCards) {
        this.rateCards = rateCards;
    }
}

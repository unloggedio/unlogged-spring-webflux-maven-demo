package org.unlogged.springwebfluxdemo.model.ecommerce;

import java.util.List;

public class ShippingService {

    private String id;
    private String serviceProvider;
    private List<String> operationPinCodes;
    private double shippingCostPerItem;
    private long maxCapacity;

    public ShippingService(String id, String serviceProvider, List<String> operationPinCodes, double shippingCostPerItem, long maxCapacity) {
        this.id = id;
        this.serviceProvider = serviceProvider;
        this.operationPinCodes = operationPinCodes;
        this.shippingCostPerItem = shippingCostPerItem;
        this.maxCapacity = maxCapacity;
    }

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

    @Override
    public String toString() {
        return "ShippingService{" +
                "id='" + id + '\'' +
                ", serviceProvider='" + serviceProvider + '\'' +
                ", operationPinCodes=" + operationPinCodes +
                ", shippingCostPerItem=" + shippingCostPerItem +
                ", maxCapacity=" + maxCapacity +
                '}';
    }
}

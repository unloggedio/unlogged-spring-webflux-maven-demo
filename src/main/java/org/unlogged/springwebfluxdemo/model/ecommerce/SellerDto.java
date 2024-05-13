package org.unlogged.springwebfluxdemo.model.ecommerce;

import java.util.List;

public class SellerDto {
    
    private String id;
    private String name;
    private String email;
    private int age;
    private List<ProductDto> products;
    private List<ShippingServiceDto> shippingServices;

    public SellerDto(String id, String name, String email, int age, List<ProductDto> products, List<ShippingServiceDto> shippingServices) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.products = products;
        this.shippingServices = shippingServices;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDto> products) {
        this.products = products;
    }

    public List<ShippingServiceDto> getShippingServices() {
        return shippingServices;
    }

    public void setShippingServices(List<ShippingServiceDto> shippingServices) {
        this.shippingServices = shippingServices;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", products=" + products +
                ", shippingServices=" + shippingServices +
                '}';
    }
}

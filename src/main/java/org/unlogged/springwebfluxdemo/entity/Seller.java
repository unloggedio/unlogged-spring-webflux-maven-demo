package org.unlogged.springwebfluxdemo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "sellers")
public class Seller {
    @Id
    private String id;
    private String name;
    private String email;
    private int age;
    private List<Product> products;
    private List<ShippingServices> shippingServices;

    public Seller(String id, String name, String email, int age, List<Product> products, List<ShippingServices> shippingServices) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
        this.products = products;
        this.shippingServices = shippingServices;
    }

    public Seller() { }

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<ShippingServices> getShippingServices() {
        return shippingServices;
    }

    public void setShippingServices(List<ShippingServices> shippingServices) {
        this.shippingServices = shippingServices;
    }
}

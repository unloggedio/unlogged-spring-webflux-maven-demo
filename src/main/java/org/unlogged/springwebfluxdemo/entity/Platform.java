package org.unlogged.springwebfluxdemo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "platforms")
public class Platform {
    @Id
    private String id;
    private String platformName;
    private String registeredCountry;
    private List<String> operatingCategories;
    private List<String> listedSellerIds;

    public Platform(String id, String platformName, String registeredCountry, List<String> operatingCategories, List<String> listedSellerIds) {
        this.id = id;
        this.platformName = platformName;
        this.registeredCountry = registeredCountry;
        this.operatingCategories = operatingCategories;
        this.listedSellerIds = listedSellerIds;
    }
    public Platform() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getRegisteredCountry() {
        return registeredCountry;
    }

    public void setRegisteredCountry(String registeredCountry) {
        this.registeredCountry = registeredCountry;
    }

    public List<String> getOperatingCategories() {
        return operatingCategories;
    }

    public void setOperatingCategories(List<String> operatingCategories) {
        this.operatingCategories = operatingCategories;
    }

    public List<String> getListedSellerIds() {
        return listedSellerIds;
    }

    public void setListedSellerIds(List<String> listedSellerIds) {
        this.listedSellerIds = listedSellerIds;
    }
}

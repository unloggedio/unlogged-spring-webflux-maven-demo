package org.unlogged.springwebfluxdemo.model.ecommerce;

import java.util.List;

public class PlatformsDto {
    private String id;
    private String platformName;
    private String registeredCountry;
    private List<String> operatingCategories;
    private List<SellerDto> listedSellers;

    public PlatformsDto(String id, String platformName, String registeredCountry, List<String> operatingCategories, List<SellerDto> listedSellers) {
        this.id = id;
        this.platformName = platformName;
        this.registeredCountry = registeredCountry;
        this.operatingCategories = operatingCategories;
        this.listedSellers = listedSellers;
    }

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

    public List<SellerDto> getListedSellers() {
        return listedSellers;
    }

    public void setListedSellers(List<SellerDto> listedSellers) {
        this.listedSellers = listedSellers;
    }

    @Override
    public String toString() {
        return "PlatformsDto{" +
                "id='" + id + '\'' +
                ", platformName='" + platformName + '\'' +
                ", registeredCountry='" + registeredCountry + '\'' +
                ", operatingCategories=" + operatingCategories +
                ", listedSellers=" + listedSellers +
                '}';
    }
}

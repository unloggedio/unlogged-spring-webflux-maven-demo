package org.unlogged.springwebfluxdemo.model;

import java.util.List;

public class UniversityProfile {

    private String id;
    private String name;
    private String address;

    public UniversityProfile(University university) {
        this.id = university.id();
        this.name = university.name();
        this.address = university.address();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

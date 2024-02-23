package org.unlogged.springwebfluxdemo.model;

public class StaffSaveRequest {
    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StaffSaveRequest() {
    }

    public StaffSaveRequest(long id, String name) {
        this.id = id;
        this.name = name;
    }
}

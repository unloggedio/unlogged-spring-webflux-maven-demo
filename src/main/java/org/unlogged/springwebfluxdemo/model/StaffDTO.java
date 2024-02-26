package org.unlogged.springwebfluxdemo.model;

public class StaffDTO {
    private int id;
    private String name;

    public StaffDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public StaffDTO(Staff staff) {
        this.id = staff.id();
        this.name = staff.name();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

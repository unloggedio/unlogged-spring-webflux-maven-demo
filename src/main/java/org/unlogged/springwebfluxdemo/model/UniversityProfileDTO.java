package org.unlogged.springwebfluxdemo.model;

import java.util.List;

public class UniversityProfileDTO {

    private String id;
    private String name;
    private String address;
    private List<StaffDTO> staffList;

    public UniversityProfileDTO(UniversityProfile profile, List<StaffDTO> staffDTOS) {
        this.id = profile.getId();
        this.name = profile.getName();
        this.address = profile.getAddress();
        this.staffList = staffDTOS;
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

    public List<StaffDTO> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<StaffDTO> staffList) {
        this.staffList = staffList;
    }

    @Override
    public String toString() {
        return "UniversityProfileDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", staffList=" + staffList +
                '}';
    }
}

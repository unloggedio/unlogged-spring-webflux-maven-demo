package org.unlogged.springwebfluxdemo.model;

import java.util.List;

public class UniversityProfileV2 {

    private Integer id;
    private String name;
    private List<Coffee> beverages;
    private List<Person> listOfStudents;
    private List<Person> listOfSeniorMembers;
    private List<StaffDTO> staffDTOList;

    public UniversityProfileV2(Integer id, String name, List<Coffee> beverages, List<Person> listOfStudents, List<Person> listOfSeniorMembers, List<StaffDTO> staffDTOList) {
        this.id = id;
        this.name = name;
        this.beverages = beverages;
        this.listOfStudents = listOfStudents;
        this.listOfSeniorMembers = listOfSeniorMembers;
        this.staffDTOList = staffDTOList;
    }

    public UniversityProfileV2() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Coffee> getBeverages() {
        return beverages;
    }

    public void setBeverages(List<Coffee> beverages) {
        this.beverages = beverages;
    }

    public List<Person> getListOfStudents() {
        return listOfStudents;
    }

    public void setListOfStudents(List<Person> listOfStudents) {
        this.listOfStudents = listOfStudents;
    }

    public List<Person> getListOfSeniorMembers() {
        return listOfSeniorMembers;
    }

    public void setListOfSeniorMembers(List<Person> listOfSeniorMembers) {
        this.listOfSeniorMembers = listOfSeniorMembers;
    }

    public List<StaffDTO> getStaffDTOList() {
        return staffDTOList;
    }

    public void setStaffDTOList(List<StaffDTO> staffDTOList) {
        this.staffDTOList = staffDTOList;
    }

    @Override
    public String toString() {
        return "UniversityProfileV2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", beverages=" + beverages +
                ", listOfStudents=" + listOfStudents +
                ", listOfSeniorMembers=" + listOfSeniorMembers +
                ", staffDTOList=" + staffDTOList +
                '}';
    }
}

package org.unlogged.springwebfluxdemo.model;

import java.util.List;

public class UniversityFoodInfo {
    private Integer universityId;
    private String universityName;
    private List<Coffee> beveragesAvailable;

    public Integer getUniversityId() {
        return universityId;
    }

    public void setUniversityId(Integer universityId) {
        this.universityId = universityId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public List<Coffee> getBeveragesAvailable() {
        return beveragesAvailable;
    }

    public void setBeveragesAvailable(List<Coffee> beveragesAvailable) {
        this.beveragesAvailable = beveragesAvailable;
    }

    @Override
    public String toString() {
        return "UniversityFoodInfo{" +
                "universityId='" + universityId + '\'' +
                ", universityName='" + universityName + '\'' +
                ", beveragesavailable=" + beveragesAvailable +
                '}';
    }
}

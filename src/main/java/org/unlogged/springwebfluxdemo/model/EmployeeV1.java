package org.unlogged.springwebfluxdemo.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.Objects;

public class EmployeeV1 implements Serializable {
    private String id;
    private String name;
    private String department;

    public EmployeeV1(String id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public EmployeeV1() {
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeV1 employeeV1)) return false;
        return Objects.equals(id, employeeV1.id) && Objects.equals(name, employeeV1.name) && Objects.equals(department, employeeV1.department);
    }

    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            // Handle serialization exception (e.g., log the error)
            throw new RuntimeException("Error serializing EmployeeV1 to JSON", e);
        }
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, department);
    }
}
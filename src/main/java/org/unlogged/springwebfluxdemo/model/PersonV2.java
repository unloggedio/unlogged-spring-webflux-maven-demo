package org.unlogged.springwebfluxdemo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class PersonV2 extends AuditEntity {

    @Size(min = 3, max = 15, message = "Name must be between 3 and 15 characters.")
    String name;

    @Size(min = 3, max = 15, message = "username must not be empty.")
    String username;

    Long phone;

    @Size(max = 255, message = "Must be a valid email id")
    String emailPrimary;

    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
    String emailSecondary;

    int age;

    @Size(min = 6, max = 15, message = "password must not be empty.")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(max = 100)
    String password;

    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    LocalDate dob;

    Boolean isAdult;

    @JsonManagedReference
    String address;

    public PersonV2() {
    }

    public PersonV2(String name, String username, Long phone, String emailPrimary, String emailSecondary, int age, String password, LocalDate dob, Boolean isAdult, String address) {
        this.name = name;
        this.username = username;
        this.phone = phone;
        this.emailPrimary = emailPrimary;
        this.emailSecondary = emailSecondary;
        this.age = age;
        this.password = password;
        this.dob = dob;
        this.isAdult = isAdult;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getEmailPrimary() {
        return emailPrimary;
    }

    public void setEmailPrimary(String emailPrimary) {
        this.emailPrimary = emailPrimary;
    }

    public String getEmailSecondary() {
        return emailSecondary;
    }

    public void setEmailSecondary(String emailSecondary) {
        this.emailSecondary = emailSecondary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Boolean getAdult() {
        return isAdult;
    }

    public void setAdult(Boolean adult) {
        isAdult = adult;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

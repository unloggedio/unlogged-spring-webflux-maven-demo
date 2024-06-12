package org.unlogged.springwebfluxdemo.model.dto;

import jakarta.validation.constraints.*;

public class UserDto {

    private String id;

    @NotEmpty
    @Size(min=1, message = "User name must be greater than 1 characters")
    private String name;

    @Email(message = "Invalid Email")
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{5,20}$",
            message = "Password must be 5 to 20 characters long, "
                    + "contain at least one digit, one lowercase letter, "
                    + "one uppercase letter, and one special character "
                    + "from @#$%^&+=!")
    private String password;

    @NotNull
    @Positive(message = "Value must be positive")
    @Max(120)
    private int age;

    public UserDto() {}

    public UserDto(String id, String name, String email, String password, int age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }
}

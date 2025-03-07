package se.java.security.dto;

import jakarta.validation.constraints.*;
import se.java.security.models.Role;

import java.util.Set;

public class RegisterRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private Set<Role> roles;
    @NotBlank
    private String firstName;
    @NotNull
    @Min(value = 18, message = "Minimum age is 18")
    @Max(value = 100, message = "Maximum age is 100")
    private Integer age;
    @Email
    @NotBlank
    private String email;


    public RegisterRequest(String username, String password, Set<Role> roles, Integer age, String email, String firstName) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.age = age;
        this.email = email;
        this.firstName = firstName;
    }

    public @NotBlank String getUsername() {
        return username;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

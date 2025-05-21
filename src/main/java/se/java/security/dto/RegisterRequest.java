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
    @Size(max = 16, message = "First name cannot be longer than 16 characters")
    private String firstName;

    @NotBlank
    @Size(max = 16, message = "Last name cannot be longer than 16 characters")
    private String lastName;

    @Email
    @NotBlank
    private String email;

    @NotNull
    @Min(value = 18, message = "Minimum age is 18")
    @Max(value = 100, message = "Maximum age is 100")
    private Integer age;

    @NotBlank
    @Size(max = 100, message = "Bio cannot be longer than 16 characters")
    private String bio;

    @NotBlank
    private String profilePic;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public RegisterRequest(String username, String password, Set<Role> roles, String firstName, String lastName, String email, Integer age, String bio, String profilePic) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.bio = bio;
        this.profilePic = profilePic;
    }
}

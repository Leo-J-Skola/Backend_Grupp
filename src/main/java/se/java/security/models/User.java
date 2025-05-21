package se.java.security.models;

import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "users")
public class User {
    @Id
    private String id;

    @Indexed(unique = true)
    @NotEmpty(message = "Username cannot be empty")
    private String username;

    private String password;

    private Set<Role> roles;

    @NotEmpty(message = "First name cannot be empty")
    @Size(max = 16, message = "First name cannot be longer than 16 characters")
    private String firstName;

    @Size(max = 16, message = "Last name cannot be longer than 16 characters")
    private String lastName;

    @Min(value = 18, message = "Minimum age is 18")
    private Integer age;

    @Size(max = 100, message = "A bio cannot be longer than 100 characters")
    private String bio; //this is "description" where a user can write something about themselves

    @Value("https://homi.se/default-profile-pic.jpg") //we can use this to set a default profile picture
    private String profilePic;                        //to users if they don't want to upload one themselves

    @Indexed(unique = true)
    @Email(message = "Please enter a valid email address") //this is an annotation for email validation in Spring that checks if the email is valid
    private String email;

    public User() {
    }

    public User(String username, String password, Set<Role> roles, String firstName, String lastName, Integer age, String bio, String profilePic) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.bio = bio;
        this.profilePic = profilePic;
    }


    public String getId() {
        return id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setId(String id) {
        this.id = id;
    }
}

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

    @Indexed(unique = true)
    @Email //this is an annotation for email validation in Spring that checks if the email is valid
    @NotEmpty(message = "Please enter a valid email address")
    private String email;

    @Indexed(unique = true)
    @Pattern(
            regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", //this is valid: +46 7234567890. So country number +46 and 10 numbers
            message = "Please enter a valid phone number"
    )
    private String phonenumber;

    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()\\-_=+{};:,<.>])(?=.{8,})" +
                    ".*$",
            message = "Password must be at least 8 characters long and contain at least " +
                    "one uppercase letter, one number, and one special character"
    )
    private String password;
    private Set<Role> roles;

    @NotEmpty(message = "First name cannot be empty")
    @Size(max = 16, message = "First name cannot be longer than 16 characters")
    private String firstName;

    @Size(max = 16, message = "Last name cannot be longer than 16 characters")
    private String lastName;

    @Value("https://homi.se/default-profile-pic.jpg") //we can use this to set a default profile picture
    private String profilePic;                        //to users if they don't want to upload one themselves


    public User() {
    }

    public User(String username, String password, Set<Role> roles, String firstname, String lastname, String profilepic) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.firstName = firstname;
        this.lastName = lastname;
        this.profilePic = profilepic;
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
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

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

}

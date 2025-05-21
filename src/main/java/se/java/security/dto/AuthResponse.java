package se.java.security.dto;

import se.java.security.models.Role;

import java.util.Set;

public class AuthResponse {
    private String jwtToken;
    private String username;
    private Set<Role> roles;
    private String userId;
    private String profilePic;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private String bio;

    public AuthResponse(String jwtToken, String username, Set<Role> roles, String userId, String profilePic, String firstName, String lastName, String email, int age, String bio) {
        this.jwtToken = jwtToken;
        this.username = username;
        this.roles = roles;
        this.userId = userId;
        this.profilePic = profilePic;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.bio = bio;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}

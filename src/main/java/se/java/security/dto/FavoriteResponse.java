package se.java.security.dto;

import jakarta.validation.constraints.NotEmpty;

public class FavoriteResponse {

    @NotEmpty(message = "username cannot be empty")
    private String username;
    @NotEmpty(message = "email cannot be empty")
    private String email;
    @NotEmpty(message = "title cannot be empty")
    private String title;
    @NotEmpty(message = "description cannot be empty")
    private String description;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FavoriteResponse(String username, String email, String title, String description) {
        this.username = username;
        this.email = email;
        this.title = title;
        this.description = description;
    }

    public FavoriteResponse() {
    }
}

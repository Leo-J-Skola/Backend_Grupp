package se.java.security.dto;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

public class FavoriteResponse {

    @NotEmpty(message = "username cannot be empty")
    private String username;
    @NotEmpty(message = "email cannot be empty")
    private String email;
    @DBRef
    private List<String> favoritedListingsIds;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getFavoritedListingsIds() {
        return favoritedListingsIds;
    }

    public void setFavoritedListingsIds(List<String> favoritedListingsIds) {
        this.favoritedListingsIds = favoritedListingsIds;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public FavoriteResponse(String username, String email, List<String> favoritedListingsIds) {
        this.username = username;
        this.email = email;
        this.favoritedListingsIds = favoritedListingsIds;
    }

    public FavoriteResponse() {
    }
}

package se.java.security.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

public class FavoriteDTO {
    @Id
    private String favoriteId;
    @NotBlank(message = "userId cannot be empty")
    private String userId;
    @NotBlank(message = "listingId cannot be empty")
    private String listingId;

    public String getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(String favoriteId) {
        this.favoriteId = favoriteId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getListingId() {
        return listingId;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
    }

    public FavoriteDTO(String favoriteId, String userId, String listingId) {
        this.favoriteId = favoriteId;
        this.userId = userId;
        this.listingId = listingId;
    }

    public FavoriteDTO() {
    }
}
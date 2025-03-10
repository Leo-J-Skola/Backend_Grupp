package se.java.security.models;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "favorites")
public class Favorite {
    @Id
    private String favoriteId;
    @NotEmpty(message = "userId cannot be empty")
    private String userId;
    @NotEmpty(message = "hostId cannot be empty")
    private String hostId;
    @NotEmpty(message = "listingId cannot be empty")
    private String listingId;
    @CreatedDate
    private LocalDate createdDate;

    public Favorite() {
    }

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

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getListingId() {
        return listingId;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
    }

    public Favorite(String favoriteId, String userId, String hostId, String listingId) {
        this.favoriteId = favoriteId;
        this.userId = userId;
        this.hostId = hostId;
        this.listingId = listingId;
    }
}

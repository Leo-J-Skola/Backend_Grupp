package se.java.security.models;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Document(collection = "favorites")
public class Favorite {
    @Id
    private String id;

    @DBRef
    @NotEmpty(message = "userId cannot be null")
    private User userId;

    @DBRef
    @NotEmpty(message = "hostId cannot be null")
    private User hostId;

    @DBRef
    @NotEmpty(message = "listingId cannot be null")
    private Listing listingId;

    @CreatedDate
    private LocalDate createdDate;

    @DBRef
    @NotEmpty(message = "Favorites must contain atleast one object")
    private List<Favorite> favorites;

    private Map<String, Integer> quantities;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public User getHostId() {
        return hostId;
    }

    public void setHostId(User hostId) {
        this.hostId = hostId;
    }

    public Listing getListingId() {
        return listingId;
    }

    public void setListingId(Listing listingId) {
        this.listingId = listingId;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public List<Favorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    public Map<String, Integer> getQuantities() {
        return quantities;
    }

    public void setQuantities(Map<String, Integer> quantities) {
        this.quantities = quantities;
    }

    public Favorite() {
    }

    public Favorite(String id, User userId, User hostId, Listing listingId, LocalDate createdDate, List<Favorite> favorites, Map<String, Integer> quantities) {
        this.id = id;
        this.userId = userId;
        this.hostId = hostId;
        this.listingId = listingId;
        this.createdDate = createdDate;
        this.favorites = favorites;
        this.quantities = quantities;
    }
}

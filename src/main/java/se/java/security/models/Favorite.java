package se.java.security.models;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "favorites")
public class Favorite {
    @Id
    private String id;

    @DBRef
    @NotEmpty(message = "userId cannot be empty")
    private User userId;

    @DBRef
    @NotEmpty(message = "listingId cannot be empty")
    private Listing listingId;

    @CreatedDate
    private LocalDate createdDate;

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

    public Favorite() {
    }

    public Favorite(String id, User userId, Listing listingId, LocalDate createdDate) {
        this.id = id;
        this.userId = userId;
        this.listingId = listingId;
        this.createdDate = createdDate;
    }
}

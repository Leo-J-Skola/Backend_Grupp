package se.java.security.models;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "ratings")
public class Rating {
    @Id
    private String id;

    @NotBlank(message = "userId cannot be empty")

    private User userId;

    @NotBlank(message = "listingId cannot be empty")
    @DBRef
    private Listing listingId;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private int rating;

    @CreatedDate
    private LocalDateTime createdAt;

    public Rating(String id, User userId, Listing listingId, int rating, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.listingId = listingId;
        this.rating = rating;
        this.createdAt = createdAt;
    }

    public Rating() {
    }

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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
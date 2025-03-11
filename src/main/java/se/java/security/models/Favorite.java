package se.java.security.models;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;

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
    private Date createdDate = new Date();

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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Favorite(String id, User userId, Listing listingId, Date createdDate) {
        this.id = id;
        this.userId = userId;
        this.listingId = listingId;
        this.createdDate = createdDate;
    }

    public Favorite() {
    }
}

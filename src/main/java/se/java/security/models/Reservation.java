package se.java.security.models;


import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "reservations")
public class Reservation {

    @Id
    private String id;

    @NotBlank(message = "listingId cannot be null")
    private String listingId;

    @NotBlank(message = "userId cannot be null")
    private String userId;

    @NotBlank(message = "startDate cannot be null")
    private Date startDate;

    @NotBlank(message = "endDate cannot be null")
    private Date endDate;

    @NotBlank(message = "status cannot be null")
    private String status;

    public Reservation() {
    }

    public Reservation(String id, String listingId, String userId, Date startDate, Date endDate, String status) {
        this.id = id;
        this.listingId = listingId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getListingId() {
        return listingId;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

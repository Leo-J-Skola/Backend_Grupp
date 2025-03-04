package se.java.security.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "bookings")
public class Booking {
    @Id
    @NotBlank
    private String bookingId;

    @NotEmpty(message = "User id can not be empty")
    private String userId;

    @NotEmpty(message = "Listing id can not be empty")
    private String listingId;

    @NotEmpty(message = "Status can not be empty")
    private Status status;

    @NotNull(message = "fee can not be null")
    private double fee;

    @Valid
    @CreatedDate
    private LocalDateTime createdDate = LocalDateTime.now();

    @Valid
    @CreatedDate
    private LocalDateTime lastModifiedDate;

    @NotNull(message = "Total amount can not be null")
    private double totalAmount;

    private boolean acceptedByHost;

    public Booking(String bookingId, String userId, String listingId, Status status, double fee, LocalDateTime createdDate, LocalDateTime lastModifiedDate, double totalAmount) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.listingId = listingId;
        this.status = status;
        this.fee = fee;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.totalAmount = totalAmount;
    }

    public Booking() {
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public boolean isAcceptedByHost() {
        return acceptedByHost;
    }

    public void setAcceptedByHost(boolean acceptedByHost) {
        this.acceptedByHost = acceptedByHost;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}

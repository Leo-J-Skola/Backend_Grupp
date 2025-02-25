package se.java.security.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "bookings")
public class Booking {
    @Id
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
    private Date startDate;

    @Valid
    @CreatedDate
    private Date endDate;

    @NotNull(message = "Total amount can not be null")
    private double totalAmount;

    public Booking(String bookingId, String userId, String listingId, Status status, double fee, Date startDate, Date endDate, double totalAmount) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.listingId = listingId;
        this.status = status;
        this.fee = fee;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public @NotEmpty(message = "User id can not be empty") String getUserID() {
        return userId;
    }

    public void setUserID(@NotEmpty(message = "User id can not be empty") String userId) {
        this.userId = userId;
    }

    public @NotEmpty(message = "Listing id can not be empty") String getListingID() {
        return userId;
    }

    public void setListingID(@NotEmpty(message = "Listing id can not be empty") String listingId) {
        this.listingId = listingId;
    }

    public @NotEmpty(message = "Status can not be empty") Status getStatus() {
        return status;
    }

    public void setStatus(@NotEmpty(message = "Status can not be empty") Status status) {
        this.status = status;
    }

    @NotNull(message = "fee can not be null")
    public double getFee() {
        return fee;
    }

    public void setFee(@NotNull(message = "fee can not be null") double fee) {
        this.fee = fee;
    }

    public @Valid Date getStartDate() {
        return startDate;
    }

    public void setStartDate(@Valid Date startDate) {
        this.startDate = startDate;
    }

    public @Valid Date getEndDate() {
        return endDate;
    }

    public void setEndDate(@Valid Date endDate) {
        this.endDate = endDate;
    }

    @NotNull(message = "Total amount can not be null")
    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(@NotNull(message = "Total amount can not be null") double totalAmount) {
        this.totalAmount = totalAmount;
    }
}

package se.java.security.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document(collection = "bookings")
@Valid
public class Booking {
    @Id
    private String id;

    private String userId;

    @DBRef
    private Listing listingId;

    private BookingStatus bookingStatus;

    @NotNull(message = "fee can not be null")
    private double fee;

    @NotNull(message = "Total amount can not be null")
    private double totalAmount;

    @NotBlank(message = "startDate cannot be blank")
    private Date startDate;

    @NotBlank(message = "endDate cannot be blank")
    private Date endDate;

    public Booking() {
    }

    public Booking(String id, String userId, Listing listingId, BookingStatus bookingStatus, double fee, double totalAmount, Date startDate, Date endDate) {
        this.id = id;
        this.userId = userId;
        this.listingId = listingId;
        this.bookingStatus = bookingStatus;
        this.fee = fee;
        this.totalAmount = totalAmount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Listing getListingId() {
        return listingId;
    }

    public void setListingId(Listing listingId) {
        this.listingId = listingId;
    }

    public BookingStatus getStatus() {
        return bookingStatus;
    }

    public void setStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
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
}

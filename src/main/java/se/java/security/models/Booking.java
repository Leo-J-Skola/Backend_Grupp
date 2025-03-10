package se.java.security.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "bookings")
@Valid
public class Booking {
    @Id
    @NotBlank
    @CreatedDate
    private String bookingId;

    @NotEmpty(message = "User id can not be empty")
    private String userId;

    @NotEmpty(message = "Listing id can not be empty")
    private String listingId;

    private Status status;

    @NotNull(message = "fee can not be null")
    private double fee;

    @NotNull(message = "Total amount can not be null")
    private double totalAmount;

    private Set<Availability> bookedDates;


    public Booking(String bookingId, String userId, String listingId, Status status, double fee, double totalAmount, Set<Availability> bookedDates) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.listingId = listingId;
        this.status = status;
        this.fee = fee;
        this.totalAmount = totalAmount;
        this.bookedDates = bookedDates;
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

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Set<Availability> getBookedDates() {
        return bookedDates;
    }

    public void setBookedDates(Set<Availability> bookedDates) {
        this.bookedDates = bookedDates;
    }
}

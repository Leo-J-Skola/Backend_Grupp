package se.java.security.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import se.java.security.models.Availability;
import se.java.security.models.Status;

import java.util.HashSet;
import java.util.Set;

public class BookingDTO {
    @Id
    private String bookingId;
    @NotBlank(message = "user id cannot be null")
    private String userId;
    @NotBlank(message = "listing id cannot be null")
    private String listingId;
    private Status status;
    @NotNull

    private Set<Availability> bookedDates;

    @NotNull
    private double totalAmount;


    public BookingDTO(String userId, String listingId, Status status,double totalAmount) {
        this.userId = userId;
        this.listingId = listingId;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public BookingDTO() {
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

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
    private boolean acceptedByHost;

    private Set<Availability> availabilities =  new HashSet<>();

    @NotNull
    private double totalAmount = 150.0;


    public BookingDTO(String userId, String listingId, Status status, boolean acceptedByHost, Set<Availability> availabilities, double totalAmount) {
        this.userId = userId;
        this.listingId = listingId;
        this.status = status;
        this.acceptedByHost = acceptedByHost;
        this.availabilities = availabilities;
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

    public Set<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(Set<Availability> availabilities) {
        this.availabilities = availabilities;
    }
}

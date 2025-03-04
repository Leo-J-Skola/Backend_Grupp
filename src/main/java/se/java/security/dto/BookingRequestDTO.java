package se.java.security.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import se.java.security.models.Availability;
import se.java.security.models.Status;

import java.util.Set;

public class BookingRequestDTO {
    @Id
    private String bookingId;
    @NotBlank
    private String userId;
    @NotBlank
    private String listingId;
    private Status status;
    @NotNull
    private boolean acceptedByHost;

    private Set<Availability> availabilities;

    public BookingRequestDTO(String bookingId, String userId, String listingId, Status status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.listingId = listingId;
        this.status = status;
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

    public Set<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(Set<Availability> availabilities) {
        this.availabilities = availabilities;
    }
}

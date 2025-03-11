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
    private String id;
    private Status status;
    @NotNull
    private Set<Availability> availabilities =  new HashSet<>();

    @NotNull
    private double totalAmount;

    public BookingDTO(Status status, Set<Availability> availabilities, double totalAmount) {
        this.status = status;
        this.availabilities = availabilities;
        this.totalAmount = totalAmount;
    }

    public BookingDTO() {
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

    public Set<Availability> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(Set<Availability> availabilities) {
        this.availabilities = availabilities;
    }
}

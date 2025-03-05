package se.java.security.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.Date;

public class Availability {
    private Booking booking;
    @NotNull(message = "Start date must not be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "End date must not be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    public Availability(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Availability() {
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public @NotBlank LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(@NotBlank LocalDate endDate) {
        this.endDate = endDate;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}

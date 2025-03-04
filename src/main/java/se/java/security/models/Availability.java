package se.java.security.models;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.Date;

public class Availability {


    private LocalDate startDate;
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
}

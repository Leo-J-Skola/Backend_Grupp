package se.java.security.models;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public class Availability {

    @NotBlank
    private Date startDate;
    @NotBlank
    private Date endDate;

    public Availability(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Availability() {
    }

    public @NotBlank Date getStartDate() {
        return startDate;
    }

    public void setStartDate(@NotBlank Date startDate) {
        this.startDate = startDate;
    }

    public @NotBlank Date getEndDate() {
        return endDate;
    }

    public void setEndDate(@NotBlank Date endDate) {
        this.endDate = endDate;
    }
}

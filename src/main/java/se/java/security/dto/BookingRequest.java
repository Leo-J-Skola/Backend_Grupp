package se.java.security.dto;

import se.java.security.models.Availability;
import se.java.security.models.Status;

import java.util.Set;

public class BookingRequest {
    private String listingId;
    private Status status;
    private double fee;
    private double totalAmount;
    private Set<Availability> bookedDates;

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

    public BookingRequest(String userId, String listingId, Status status, double fee, double totalAmount, Set<Availability> bookedDates) {
        this.listingId = listingId;
        this.status = status;
        this.fee = fee;
        this.totalAmount = totalAmount;
        this.bookedDates = bookedDates;
    }

    public BookingRequest() {
    }
}


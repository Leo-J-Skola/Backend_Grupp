package se.java.security.dto;

import se.java.security.models.Status;

import java.util.Date;


public class BookingRequest {
    private String listingId;
    private Status status;
    private double fee;
    private double totalAmount;
    private String userId;
    private Date startDate;
    private Date endDate;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public BookingRequest(String listingId, Status status, double fee, double totalAmount, String userId, Date startDate, Date endDate) {
        this.listingId = listingId;
        this.status = status;
        this.fee = fee;
        this.totalAmount = totalAmount;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public BookingRequest() {
    }
}


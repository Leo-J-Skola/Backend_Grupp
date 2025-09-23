package se.java.security.dto;

import se.java.security.models.BookingStatus;

import java.util.Date;


public class BookingRequest {
    private String listingId;
    private BookingStatus bookingStatus;
    private double fee;
    private double totalAmount;
    private Date startDate;
    private Date endDate;

    public String getListingId() {
        return listingId;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
    }

    public BookingStatus getStatus() {
        return bookingStatus;
    }

    public void setStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public double getTotalAmount() {
        return totalAmount;
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

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BookingRequest() {
    }
}


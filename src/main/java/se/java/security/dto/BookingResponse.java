package se.java.security.dto;

import se.java.security.models.Availability;
import se.java.security.models.Status;

public class BookingResponse {

    private String bookingId;
    private String message;
    private Status status;

    public BookingResponse(String bookingId, String message, Status status) {
        this.bookingId = bookingId;
        this.message = message;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
}

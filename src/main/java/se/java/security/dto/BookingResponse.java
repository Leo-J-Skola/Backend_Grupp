package se.java.security.dto;

import se.java.security.models.BookingStatus;

public class BookingResponse {

    private String bookingId;
    private String message;
    private BookingStatus bookingStatus;

    public BookingResponse(String bookingId, String message, BookingStatus bookingStatus) {
        this.bookingId = bookingId;
        this.message = message;
        this.bookingStatus = bookingStatus;
    }

    public BookingStatus getStatus() {
        return bookingStatus;
    }

    public void setStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
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

package se.java.security.factory;

import se.java.security.models.Booking;
import se.java.security.models.Listing;
import se.java.security.models.Status;

import java.util.Date;

public class BookingFactory {
    public static Booking createBooking(Status status, String userId, Listing listingId, double fee, double totalAmount, Date startDate, Date endDate) {

        // Set status to pending if status is null
        if(status == null ){
            status = Status.PENDING;
        }
        // Throws an error if userId is null
        if(userId == null){
            throw new IllegalArgumentException("userId is null");
        }
        // Throws an error if listingId is null
        if(listingId == null){
            throw new IllegalArgumentException("listingId is null");
        }
        // Throws an error if fee is less than or equal to zero
        if(fee <= 0 ){
            throw new IllegalArgumentException("Fee can't be less than or equal to 0");
        }
        // Throws an error if total amount is less than or equal to zero
        if(totalAmount <= 0 ){
            throw new IllegalArgumentException("Total amount can't be less than or equal to 0");
        }
        // Throws an error if startDate or endDate is a null value
        if(startDate == null || endDate == null){
            throw new IllegalArgumentException("startDate or endDate can't be null");
        }
        // Validation check to see if endDate is before the startDate
        if(endDate.before(startDate)){
            throw new IllegalArgumentException("endDate can't be before startDate");
        }

        // Create a new booking object
        Booking booking = new Booking();
        booking.setStatus(status);
        booking.setUserId(userId);
        booking.setListingId(listingId);
        booking.setFee(fee);
        booking.setTotalAmount(totalAmount);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        return booking;
    }
}

package se.java.security.factory;

import se.java.security.models.Booking;
import se.java.security.models.Listing;
import se.java.security.models.Status;

import java.util.Date;

public class BookingFactory {
    public static Booking createBooking(Status status, String userId, Listing listingId, double fee, double totalAmount, Date startDate, Date endDate) {


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

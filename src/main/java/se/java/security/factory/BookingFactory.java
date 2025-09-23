package se.java.security.factory;

import org.springframework.stereotype.Component;
import se.java.security.models.Booking;
import se.java.security.models.Listing;
import se.java.security.models.BookingStatus;

import java.util.Date;


@Component
public class BookingFactory {

    public Booking createBooking(BookingStatus bookingStatus, String userId, Listing listing, double fee, double totalAmount, Date startDate, Date endDate) {


        // Create a new booking object
        Booking booking = new Booking();
        booking.setStatus(bookingStatus);
        booking.setUserId(userId);
        booking.setListingId(listing);
        booking.setFee(fee);
        booking.setTotalAmount(totalAmount);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        return booking;
    }
}

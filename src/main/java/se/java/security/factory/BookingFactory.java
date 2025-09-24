package se.java.security.factory;

import org.springframework.stereotype.Component;
import se.java.security.models.Booking;
import se.java.security.models.Listing;
import se.java.security.models.Status;
import se.java.security.repository.BookingRepository;

import java.time.LocalDate;


@Component
public class BookingFactory {

    public Booking createBooking(Status status, String userId, Listing listing, double fee, double totalAmount, LocalDate startDate, LocalDate endDate) {


        // Create a new booking object
        Booking booking = new Booking();
        booking.setStatus(status);
        booking.setUserId(userId);
        booking.setListingId(listing);
        booking.setFee(fee);
        booking.setTotalAmount(totalAmount);
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        return booking;
    }
}

package se.java.security.services.strategy;

import org.springframework.stereotype.Component;
import se.java.security.dto.BookingRequest;
import se.java.security.models.Booking;

import java.util.List;

@Component
public class OverlapBookingValidationStrategy implements BookingValidationStrategy {

    @Override
    public boolean isValid(BookingRequest bookingRequest, List<Booking> existingBookings) {
        for (Booking booking : existingBookings) {
            if (bookingRequest.getStartDate().compareTo(booking.getEndDate()) < 0 &&
            booking.getStartDate().compareTo(bookingRequest.getEndDate()) <= 0) {
                return true;
            }
        }
        return false;
    }
}

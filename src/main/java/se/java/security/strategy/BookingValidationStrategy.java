package se.java.security.strategy;

import se.java.security.dto.BookingRequest;
import se.java.security.models.Booking;

import java.util.List;

public interface BookingValidationStrategy {
    boolean isValid(BookingRequest bookingRequest, List<Booking> existingBookings);
}

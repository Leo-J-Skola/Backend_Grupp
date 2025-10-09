package se.java.security.state;

import se.java.security.models.Booking;

public class DeclinedBookingState implements BookingState {

    @Override
    public void accept(Booking booking) {
        throw new IllegalStateException("Declined booking cannot be accepted");
    }

    @Override
    public void decline(Booking booking) {
        throw new IllegalStateException("Booking has already been declined");
    }
}
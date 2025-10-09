package se.java.security.state;

import se.java.security.models.Booking;

public class AcceptedBookingState implements BookingState {

    @Override
    public void accept(Booking booking) {
        throw new IllegalStateException("The booking has already been accepted!");
    }

    @Override
    public void decline(Booking booking) {
        throw new IllegalStateException("The booking has already been accepted!");
    }
}

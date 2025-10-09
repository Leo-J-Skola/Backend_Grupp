package se.java.security.state;

import se.java.security.models.Booking;

public class DeclinedBookingState implements BookingState {

    @Override
    public void accept(Booking booking) {
        throw new IllegalStateException("The booking has already been declined!");
    }

    @Override
    public void decline(Booking booking) {
        throw new IllegalStateException("The booking has already been declined!");
    }
}
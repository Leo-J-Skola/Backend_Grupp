package se.java.security.state;

import se.java.security.models.Booking;

public class AcceptedBookingState implements BookingState {

    @Override
    public void accept(Booking booking) {
        throw new IllegalStateException("Booking is already accepted");
    }

    @Override
    public void decline(Booking booking) {
        throw new IllegalStateException("Accepted booking cannot be declined");
    }

    @Override
    public void printBookingStatus() {

    }
}

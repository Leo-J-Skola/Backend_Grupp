package se.java.security.state;

import se.java.security.models.Booking;

public interface BookingState {

    void accept(Booking booking);
    void decline(Booking booking);

}

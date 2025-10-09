package se.java.security.state;

import se.java.security.models.Booking;
import se.java.security.models.BookingStatus;

public class PendingBookingState  implements BookingState {

    @Override
    public void accept(Booking booking) {
        booking.setBookingStatus(BookingStatus.ACCEPTED);
    }

    @Override
    public void decline(Booking booking) {
        booking.setBookingStatus(BookingStatus.DECLINED);
    }

    @Override
    public void printBookingStatus() {

    }
}

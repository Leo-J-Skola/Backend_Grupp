package se.java.security.state;

import se.java.security.models.Booking;
import se.java.security.models.BookingStatus;

public class BookingStateHandler {

    public static BookingState getState(Booking booking) {
        BookingStatus bookingStatus = booking.getBookingStatus();

        if (bookingStatus == BookingStatus.PENDING) {
            return new PendingBookingState();
        }

        if (bookingStatus == BookingStatus.ACCEPTED) {
            return new AcceptedBookingState();
        }

        if (bookingStatus == BookingStatus.DECLINED) {
            return new DeclinedBookingState();
        }

        throw new IllegalStateException("The booking status does not exist" + bookingStatus);
    }
}

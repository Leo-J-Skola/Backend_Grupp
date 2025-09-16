package se.java.security.authorization;

import org.springframework.stereotype.Service;
import se.java.security.exceptions.UnauthorizedException;
import se.java.security.models.User;
import se.java.security.models.Booking;

@Service
public class BookingAuthorizationService {

        /* This checks if the user is the owner of
     the booking before letting him update or delete it */
    public void checkIfBookingOwner(User user, Booking booking) {
        if (!booking.getUserId().equals(user.getId())) {
            throw new UnauthorizedException("User is not the owner of the booking");
        }
    }
}

package se.java.security.services;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import se.java.security.exceptions.UnauthorizedException;
import se.java.security.models.User;
import se.java.security.models.Booking;
import se.java.security.models.Listing;
import se.java.security.repository.UserRepository;
import se.java.security.repository.BookingRepository;

public class AuthenticationService {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    public AuthenticationService(UserRepository userRepository, BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
    }

    // check if user is logged in and is authenticated
    public String validateCurrentUser () {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            throw new UnauthorizedException("User is not authenticated");
        }
        // if the user is authenticated we get his details
        Object user = authentication.getPrincipal();
        if (user instanceof UserDetails userDetails) {
            return userDetails.getUsername();
    }
        throw new UnauthorizedException("User details not found");
    }
        // get the current user from the database using the authenticated username
        public User getCurrentUser() {
            String username = validateCurrentUser();
            return userRepository.findByUsername(username)
                    .orElseThrow(() -> new UnauthorizedException("Authenticated user was not found in database"));
        }

        /* This checks if the user is the owner of
         the booking before letting him update or delete it */
        public void checkIfBookingOwner(User user, Booking booking) {
            if (!booking.getUserId().equals(user.getId())) {
                throw new UnauthorizedException("User is not the owner of the booking");
            }
        }

        /* Check if user is allowed to book a listing (for example, dont let user book his own) */
        public void checkIfUserCanBookListing(User user, Listing listing) {
            if (listing.getHostId().equals(user.getId())) {
                throw new UnauthorizedException("User cannot book his own listing");
            }
        }

            // Stub for checking booking date availability
        public void checkBookingDatesAvailability( ) {
//          if () {
//                    throw new UnauthorizedException("Booking failed. The selected dates are not available.");
//                }
                }

    public void validateBooking(User user, Listing listing) {
            checkIfUserCanBookListing(user, listing);
//          checkBookingDatesAvailability();
    }
}


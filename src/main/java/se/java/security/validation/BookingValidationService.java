package se.java.security.validation;

import org.springframework.stereotype.Service;
import se.java.security.models.User;
import se.java.security.models.Listing;
import se.java.security.authorization.ListingAuthorizationService;

@Service
public class BookingValidationService {

    private final ListingAuthorizationService listingAuthorizationService;

    public BookingValidationService(ListingAuthorizationService listingAuthorizationService) {
        this.listingAuthorizationService = listingAuthorizationService;
    }

    // Stub for checking booking date availability
    public void checkBookingDatesAvailability( ) {
//          if () {
//                    throw new UnauthorizedException("Booking failed. The selected dates are not available.");
//                }
    }
    // Call this method before creating a booking
    // Validates so a user cannot book his own listing, and checks if the dates are still available
    public void validateBooking(User user, Listing listing) {
        listingAuthorizationService.checkIfUserCanBookListing(user, listing);
        checkBookingDatesAvailability();
    }
}

package se.java.security.validation;

import se.java.security.models.User;
import se.java.security.models.Listing;
import se.java.security.authorization.ListingAuthorizationService;

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

    public void validateBooking(User user, Listing listing) {
        listingAuthorizationService.checkIfUserCanBookListing(user, listing);
        checkBookingDatesAvailability();
    }
}

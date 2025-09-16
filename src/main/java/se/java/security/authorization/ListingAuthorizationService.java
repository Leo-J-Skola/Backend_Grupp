package se.java.security.authorization;

import se.java.security.exceptions.UnauthorizedException;
import se.java.security.models.User;
import se.java.security.models.Listing;

public class ListingAuthorizationService {

    /* Check if user is allowed to book a listing (for example, dont let user book his own) */
    public void checkIfUserCanBookListing(User user, Listing listing) {
        if (listing.getHostId().equals(user.getId())) {
            throw new UnauthorizedException("User cannot book his own listing");
        }
    }
}

package se.java.security.authentication;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import se.java.security.exceptions.UnauthorizedException;
import se.java.security.models.User;
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
    public String validateCurrentUser() {
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

}


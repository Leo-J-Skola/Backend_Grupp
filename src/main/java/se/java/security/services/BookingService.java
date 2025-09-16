package se.java.security.services;

import org.springframework.stereotype.Service;
import se.java.security.authentication.AuthenticationService;
import se.java.security.dto.BookingRequest;
import se.java.security.dto.BookingResponse;
import se.java.security.exceptions.ListingNotFoundException;
import se.java.security.exceptions.UserNotFoundException;
import se.java.security.models.*;

import se.java.security.repository.BookingRepository;
import se.java.security.repository.ListingRepository;
import se.java.security.repository.UserRepository;


import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


@Service
public class BookingService {

    private final ListingRepository listingRepository;
    private final UserRepository userRepository;
    private final AuthenticationService authenticationService;

    public BookingService(ListingRepository listingRepository, UserRepository userRepository, AuthenticationService authenticationService) {
        this.listingRepository = listingRepository;
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    // Try to send a booking request
    public BookingResponse sendBookingRequest(BookingRequest bookingRequest) {

        // IMPORTANT!

        // Waiting for booking creation from BookingFactory class
        // Before this method can be completed
        authenticationService.validateCurrentUser();


        Listing listing = listingRepository.findById(bookingRequest.getListingId())
                .orElseThrow(() -> new ListingNotFoundException("Listing not found"));

        User user = userRepository.findById(bookingRequest.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return null;
    }

}

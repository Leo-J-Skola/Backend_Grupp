package se.java.security.services;

import org.springframework.stereotype.Service;
import se.java.security.authentication.AuthenticationService;
import se.java.security.dto.BookingRequest;
import se.java.security.dto.BookingResponse;
import se.java.security.exceptions.ListingNotFoundException;
import se.java.security.exceptions.UnauthorizedException;
import se.java.security.factory.BookingFactory;
import se.java.security.models.*;
import se.java.security.repository.BookingRepository;
import se.java.security.repository.ListingRepository;
import se.java.security.validation.BookingFieldValidation;


@Service
public class BookingService {

    private final AuthenticationService authenticationService;
    private final BookingFieldValidation bookingFieldValidation;
    private final BookingRepository bookingRepository;
    private final BookingFactory bookingFactory;
    private final ListingRepository listingRepository;

    public BookingService(AuthenticationService authenticationService, BookingFieldValidation bookingFieldValidation, BookingRepository bookingRepository, BookingFactory bookingFactory, ListingRepository listingRepository) {
        this.authenticationService = authenticationService;
        this.bookingFieldValidation = bookingFieldValidation;
        this.bookingRepository = bookingRepository;
        this.bookingFactory = bookingFactory;
        this.listingRepository = listingRepository;
    }


    // Try to send a booking request
    public BookingResponse sendBookingRequest(BookingRequest bookingRequest) {
        // Get user from id from authentication
        String userId = authenticationService.getCurrentUser().getId();

        bookingFieldValidation.validateBookingRequestData(bookingRequest);

        // Get listing object from database and use it when creating bookings
        Listing listing = listingRepository.findById(bookingRequest.getListingId())
                .orElseThrow(() -> new ListingNotFoundException("Listing not found"));

        Booking booking = bookingFactory.createBooking(bookingRequest.getStatus(), userId, listing, bookingRequest.getFee(), bookingRequest.getTotalAmount(), bookingRequest.getStartDate(), bookingRequest.getEndDate());
        bookingRepository.save(booking);
        String bookingId = booking.getId();
        return new BookingResponse(bookingId,"Booking successfully created",bookingRequest.getStatus());
    }
}

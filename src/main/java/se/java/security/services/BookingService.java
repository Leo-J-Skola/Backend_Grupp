package se.java.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.java.security.authentication.AuthenticationService;
import se.java.security.dto.BookingRequest;
import se.java.security.dto.BookingResponse;
import se.java.security.exceptions.BookingUnavailableException;
import se.java.security.exceptions.ListingNotFoundException;
import se.java.security.factory.BookingFactory;
import se.java.security.models.*;
import se.java.security.repository.BookingRepository;
import se.java.security.repository.ListingRepository;
import se.java.security.validation.BookingFieldValidation;

import java.util.List;

@Service
public class BookingService {

    private final AuthenticationService authenticationService;
    private final BookingFieldValidation bookingFieldValidation;
    @Autowired
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

        // Check if dates are booked
        if (checkBooking(bookingRequest)) {
            throw new BookingUnavailableException("You cannot book this listing during these dates");
        }

        // Get user from id from authentication
        String userId = authenticationService.getCurrentUser().getId();

        bookingFieldValidation.validateBookingRequestData(bookingRequest);

        // Get listing object from database and use it when creating bookings
        Listing listing = listingRepository.findById(bookingRequest.getListingId())
                .orElseThrow(() -> new ListingNotFoundException("Listing not found"));

        // Create booking
        Booking booking = bookingFactory.createBooking(
                bookingRequest.getStatus(),
                userId, listing,
                bookingRequest.getFee(),
                bookingRequest.getTotalAmount(),
                bookingRequest.getStartDate(),
                bookingRequest.getEndDate());
        bookingRepository.save(booking);
        String bookingId = booking.getId();
        return new BookingResponse(bookingId,"Booking successfully created",bookingRequest.getStatus());
    }

    /*
    checks if the booking request startdate to end date is overlapping
    with any existing bookings that has status pending or booked
     */
    public boolean checkBooking(BookingRequest bookingRequest) {

        Listing listing = listingRepository.findById(bookingRequest.getListingId())
                .orElseThrow(() -> new ListingNotFoundException("Listing not found"));

        List<Booking> existingBookings = bookingRepository.findByListingIdAndBookingStatusIn(
                listing,
                List.of(BookingStatus.PENDING, BookingStatus.BOOKED)
        );

        for (Booking booking : existingBookings) {
            if (bookingRequest.getStartDate().compareTo(booking.getEndDate()) <= 0 &&
                    booking.getStartDate().compareTo(bookingRequest.getEndDate()) <= 0) {
                return true;
            }
        }
        return false;
    }
}

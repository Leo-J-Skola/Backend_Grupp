package se.java.security.services;

import org.springframework.stereotype.Service;
import se.java.security.authentication.AuthenticationService;
import se.java.security.dto.BookingRequest;
import se.java.security.dto.BookingResponse;
import se.java.security.exceptions.BookingUnavailableException;
import se.java.security.exceptions.ListingNotFoundException;
import se.java.security.factory.BookingFactory;
import se.java.security.models.Booking;
import se.java.security.models.BookingStatus;
import se.java.security.models.Listing;
import se.java.security.repository.BookingRepository;
import se.java.security.repository.ListingRepository;
import se.java.security.validation.BookingFieldValidation;

import java.util.List;

@Service
public class BookingService {

    private final AuthenticationService authenticationService;
    private final BookingFieldValidation bookingFieldValidation;
    private final BookingRepository bookingRepository;
    private final BookingFactory bookingFactory;
    private final ListingRepository listingRepository;
    private final PriceCalculationService priceCalculationService;

    public BookingService(AuthenticationService authenticationService,
                          BookingFieldValidation bookingFieldValidation,
                          BookingRepository bookingRepository,
                          BookingFactory bookingFactory,
                          ListingRepository listingRepository,
                          PriceCalculationService priceCalculationService) {
        this.authenticationService = authenticationService;
        this.bookingFieldValidation = bookingFieldValidation;
        this.bookingRepository = bookingRepository;
        this.bookingFactory = bookingFactory;
        this.listingRepository = listingRepository;
        this.priceCalculationService = priceCalculationService;
    }

    // Try to send a booking request
    public BookingResponse sendBookingRequest(BookingRequest bookingRequest) {
        // Validate booking request data
        bookingFieldValidation.validateBookingRequestData(bookingRequest);

        // Ensure dates are not already booked
        if (checkBooking(bookingRequest)) {
            throw new BookingUnavailableException("You cannot book this listing during these dates");
        }

        // Get user from authentication
        String userId = authenticationService.getCurrentUser().getId();

        // Fetch listing from DB
        Listing listing = listingRepository.findById(bookingRequest.getListingId())
                .orElseThrow(() -> new ListingNotFoundException("Listing not found"));

        // Calculate total amount and fee
        double totalAmount = priceCalculationService.calculateTotalAmount(
                bookingRequest.getStartDate(),
                bookingRequest.getEndDate(),
                listing.getPricePerNight()
        );
        double fee = priceCalculationService.calculateFee(totalAmount);

        // Create booking using validated/calculated values
        Booking booking = bookingFactory.createBooking(
                bookingRequest.getStatus(),
                userId,
                listing,
                fee,
                totalAmount,
                bookingRequest.getStartDate(),
                bookingRequest.getEndDate()
        );

        bookingRepository.save(booking);

        return new BookingResponse(
                booking.getId(),
                "Booking successfully created",
                bookingRequest.getStatus()
        );
    }

    /*
     * Checks if the booking request dates overlap with
     * existing bookings that have status PENDING or BOOKED.
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

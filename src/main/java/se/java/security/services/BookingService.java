package se.java.security.services;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import se.java.security.dto.BookingDTO;
import se.java.security.dto.BookingResponse;
import se.java.security.exceptions.ListingNotFoundException;
import se.java.security.exceptions.UserNotFoundException;
import se.java.security.models.*;

import se.java.security.repository.BookingRepository;
import se.java.security.repository.ListingRepository;
import se.java.security.repository.UserRepository;

import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class BookingService {

    private final UserRepository userRepository;
    private final ListingRepository listingRepository;
    private final BookingRepository bookingRepository;

    public BookingService(UserRepository userRepository, ListingRepository listingRepository, BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.listingRepository = listingRepository;
        this.bookingRepository = bookingRepository;
    }

    // Try to send a booking request
    public BookingResponse bookingRequest(@Valid BookingDTO bookingDTO) {
        // Check if the listing exists
        if (! listingRepository.existsById(bookingDTO.getListingId())) {
            throw new ListingNotFoundException("Listing not found");
        }

        // Check if the user exists
        if (! userRepository.existsById(bookingDTO.getUserId())) {
            throw new UserNotFoundException("User not found");
        }
        // Try to retrieve the listing from the database
        Optional <Listing> optional = listingRepository.findById(bookingDTO.getListingId());
        Listing listing = listingRepository.findById(bookingDTO.getListingId())
                .orElseThrow(() -> new ListingNotFoundException("Listing not found"));

        // Create a new Booking entity with values of BookingDTO
        Booking booking = new Booking();
        booking.setListingId(bookingDTO.getListingId());
        booking.setUserId(bookingDTO.getUserId());
        booking.setStatus(Status.PENDING); // Set status to pending
        booking.setFee(1.05); // Set fee to 5%
        booking.setAcceptedByHost(false); // Set to false because the booking request has not been accepted yet

        // Create a new hash set and insert the available dates
        Set<Availability> availabilities = new HashSet<>();
        for (Availability availability : bookingDTO.getAvailabilities()) {
            Availability available = new Availability();
            available.setStartDate(availability.getStartDate());
            available.setEndDate(availability.getEndDate());
            available.setBooking(booking); // Link the Availability to the Booking
            availabilities.add(availability);
        }
        booking.setAvailabilities(availabilities); // Insert the available dates into a booking

        // Calculate the total price with the method called calculatePrice
        calculatePrice(bookingDTO, optional);

        // Save the booking
        Booking savedBooking = bookingRepository.save(booking);

        // Return a BookingResponse
        return new BookingResponse(
                savedBooking.getBookingId(), // Use savedBooking.getId() for MongoDB ObjectId
                "Successfully created booking",
                savedBooking.getStatus()
        );
    }

    // The host must accept the booking request to successfully create a booking
    public void confirmBooking(BookingDTO bookingDTO) {

        boolean isConfirmed = bookingDTO.isAcceptedByHost();

        if (isConfirmed) {
           bookingDTO.setStatus(Status.BOOKED);
           bookingDTO.setAcceptedByHost(true);
        }
    }
    // Calculates the price by taking the listings price and multiplication it by the days a user wants to book
    // After im adding the fee which is 5%
    public void calculatePrice(@Valid BookingDTO bookingDTO, Optional<Listing> listingOpt) {

        if (listingOpt.isEmpty()) {
            throw new IllegalArgumentException("Listing must not be null");
        }

        Listing listing = listingOpt.get();

        if (bookingDTO.getAvailabilities() == null || bookingDTO.getAvailabilities().isEmpty()) {
            throw new IllegalArgumentException("Listing must have availabilities");
        }

        for (Availability availability : bookingDTO.getAvailabilities()) {
            if (availability.getStartDate() == null) {
                throw new IllegalArgumentException("Start date must not be null");
            }
            if (availability.getEndDate() == null) {
                throw new IllegalArgumentException("End date must not be null");
            }

            long numberOfDays = ChronoUnit.DAYS.between(availability.getStartDate(), availability.getEndDate());
            // Calculate price by listing price, how many days a user booked and add 5% booking fee
            double totalPrice = listing.getPricePerNight() * numberOfDays * 1.05;

            bookingDTO.setTotalAmount(totalPrice);

        }
    }
    public void convertBookingDTO(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        booking.setListingId(bookingDTO.getListingId());
        booking.setUserId(bookingDTO.getUserId());
        booking.setStatus(bookingDTO.getStatus());
        booking.setFee(1.05);
        booking.setAcceptedByHost(false);

        Set<Availability> availabilities = new HashSet<>();
        for (Availability availability : bookingDTO.getAvailabilities()) {
            availability.setStartDate(availability.getStartDate());
            availability.setEndDate(availability.getEndDate());
            availability.setBooking(booking);
            availabilities.add(availability);
        }
        bookingDTO.setAvailabilities(availabilities);
    }
}

package se.java.security.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.java.security.dto.BookingDTO;
import se.java.security.dto.BookingResponse;
import se.java.security.exceptions.ListingNotFoundException;
import se.java.security.exceptions.UserNotFoundException;
import se.java.security.models.*;

import se.java.security.repository.BookingRepository;
import se.java.security.repository.ListingRepository;
import se.java.security.repository.UserRepository;

import java.time.temporal.ChronoUnit;
import java.util.Optional;



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
    @Transactional
    public BookingResponse bookingRequest(Booking bookingRequest) {

        // Check if the listing exists
        if (!listingRepository.existsById(bookingRequest.getListingId())) {
            throw new ListingNotFoundException("Listing not found");
        }

//        // Check if the user exists
        if (!userRepository.existsById(bookingRequest.getUserId())) {
            throw new UserNotFoundException("User not found");
        }
        // Try to retrieve the listing from the database
        Optional<Listing> optional = listingRepository.findById(bookingRequest.getListingId());
        Listing listing = listingRepository.findById(bookingRequest.getListingId()).orElseThrow(()
                -> new ListingNotFoundException("Listing not found"));


        // Create a new Booking entity
        Booking booking = new Booking();
        booking.setListingId(bookingRequest.getListingId());
        booking.setUserId(bookingRequest.getUserId());
        booking.setStatus(Status.PENDING); // Set status to pending
        booking.setFee(1.05); // Set fee to 5%



        // Calculate the total price with the method called calculatePrice
        calculatePrice(booking, optional);

        // Save the booking
        bookingRepository.save(booking);

        // Return a BookingResponse
        return new BookingResponse(
                booking.getBookingId(), // Use savedBooking.getId() for MongoDB ObjectId
                "Successfully created booking",
                booking.getStatus()
        );
    }

    // The host must accept the booking request to successfully create a booking
    public void confirmBooking(Booking booking) {

        booking.setStatus(Status.BOOKED);

    }

    // Calculates the price by taking the listings price and multiplication it by the days a user wants to book
    // After im adding the fee which is 5%
    public void calculatePrice(Booking booking, Optional<Listing> listingOpt) {

        if (listingOpt.isEmpty()) {
            throw new IllegalArgumentException("Listing must not be null");
        }

        Listing listing = listingOpt.get();

        for (Availability availability : booking.getBookedDates()) {
            if (availability.getStartDate() == null) {
                throw new IllegalArgumentException("Start date must not be null");
            }
            if (availability.getEndDate() == null) {
                throw new IllegalArgumentException("End date must not be null");
            }
            System.out.println("Listing Price Per Night: " + listing.getPricePerNight());
            long numberOfDays = ChronoUnit.DAYS.between(availability.getStartDate(), availability.getEndDate());
            System.out.println("Calculating price for availability:");
            System.out.println("Start Date: " + availability.getStartDate());
            System.out.println("End Date: " + availability.getEndDate());
            System.out.println("Number of Days: " + numberOfDays);
            // Calculate price by listing price, how many days a user booked and add 5% booking fee
            double totalPrice = listing.getPricePerNight() * numberOfDays * 1.05;

            booking.setTotalAmount(totalPrice);
        }
    }

    public void convertBookingDTO(Booking booking) {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setBookingId(booking.getBookingId());
        bookingDTO.setListingId(booking.getListingId());
        bookingDTO.setUserId(booking.getUserId());
        bookingDTO.setStatus(booking.getStatus());
        bookingDTO.setBookedDates(booking.getBookedDates());
    }
}
package se.java.security.services;

import org.springframework.stereotype.Service;
import se.java.security.dto.BookingRequest;
import se.java.security.dto.BookingResponse;
import se.java.security.exceptions.ListingNotFoundException;
import se.java.security.models.*;

import se.java.security.repository.BookingRepository;
import se.java.security.repository.ListingRepository;
import se.java.security.repository.UserRepository;

import java.time.temporal.ChronoUnit;
import java.util.Optional;


@Service
public class BookingService {

    private final ListingRepository listingRepository;
    private final BookingRepository bookingRepository;

    public BookingService(ListingRepository listingRepository, BookingRepository bookingRepository) {
        this.listingRepository = listingRepository;
        this.bookingRepository = bookingRepository;
    }

    // Try to send a booking request
    public BookingResponse bookingRequest(BookingRequest bookingRequest) {

        // Try to retrieve the listing from the database
        Listing listing = listingRepository.findById(bookingRequest.getListingId())
                .orElseThrow(() -> new ListingNotFoundException("Listing not found"));

        // Create a new Booking entity
        Booking booking = new Booking();
        booking.setUserId(booking.getUserId());
        booking.setListingId(listing);
        booking.setStatus(Status.PENDING);
        booking.setFee(1.05);


        // Create a new hash set for dates
        for (Availability availability : booking.getBookedDates()) {
            Availability available = new Availability();
            available.setStartDate(availability.getStartDate());
            available.setEndDate(availability.getEndDate());
            booking.getBookedDates().add(available);
        }
        // Calculate the total price with the method called calculatePrice
        calculatePrice(booking, Optional.ofNullable(listing));

        // Save the booking
        Booking savedBooking = bookingRepository.save(booking);

        // Return a BookingResponse
        return new BookingResponse(
                savedBooking.getId(), // Use savedBooking.getId() for MongoDB ObjectId
                "Successfully created booking",
                savedBooking.getStatus()
        );
    }

    // The host must accept the booking request to successfully create a booking
    public void confirmBooking(Booking booking) {

           booking.setStatus(Status.BOOKED);
    }

    // Calculates the price by taking the listings price and multiplication it by the days a user wants to book
    // After im adding the fee which is 5%
    public void calculatePrice(Booking booking, Optional<Listing> listingOpt) {

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
}

package se.java.security.services;

import org.springframework.stereotype.Service;
import se.java.security.dto.BookingRequest;
import se.java.security.dto.BookingResponse;
import se.java.security.exceptions.ListingNotFoundException;
import se.java.security.models.*;

import se.java.security.repository.BookingRepository;
import se.java.security.repository.ListingRepository;


import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


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
        booking.setUserId(bookingRequest.getUserId());
        booking.setListingId(listing);
        booking.setStatus(Status.PENDING);
        booking.setFee(0.05);
        booking.setStartDate(bookingRequest.getStartDate());
        booking.setEndDate(bookingRequest.getEndDate());




        // Calculate the total price with the method called calculatePrice
        calculatePrice(booking, listing);

        // Save the booking
        Booking savedBooking = bookingRepository.save(booking);

        // Return a BookingResponse
        return new BookingResponse(
                savedBooking.getId(), // Show booking id of created booking
                "Successfully created booking",
                savedBooking.getStatus()
        );
    }

    // The host must accept the booking request to successfully create a booking
    public void confirmBooking(Booking booking) {

        if (booking.getStartDate().before(booking.getEndDate())) {

            booking.setStatus(Status.BOOKED);
        }

        if (booking.getEndDate().before(booking.getStartDate())) {
            System.out.println("End date cannot be before start date");
        }

    }

    // Calculates the price by taking the listings price and multiplication it by the days a user wants to book
    // After im adding the fee which is 5%
    public void calculatePrice(Booking booking, Listing listing) {
            long diffInMillis = booking.getEndDate().getTime() - booking.getStartDate().getTime();
            long numberOfDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);

            System.out.println("Calculating price for availability:");
            System.out.println("Start Date: " + booking.getStartDate());
            System.out.println("End Date: " + booking.getEndDate());
            System.out.println("Number of Days: " + numberOfDays);
            // Calculate price by listing price, how many days a user booked and add 5% booking fee
            double totalPrice = listing.getPricePerNight() * numberOfDays * 1.05;
            booking.setTotalAmount(totalPrice);
        }
    }

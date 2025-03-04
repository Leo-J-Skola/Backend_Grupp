package se.java.security.services;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import se.java.security.dto.BookingRequestDTO;
import se.java.security.dto.BookingRequestResponse;
import se.java.security.exceptions.ListingNotFoundException;
import se.java.security.exceptions.UserNotFoundException;
import se.java.security.models.*;

import se.java.security.repository.BookingRepository;
import se.java.security.repository.ListingRepository;
import se.java.security.repository.UserRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


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
    public BookingRequestResponse bookingRequest(@Valid BookingRequestDTO bookingRequestDTO) {
            if (! listingRepository.existsById(bookingRequestDTO.getListingId())){
                throw new ListingNotFoundException("Listing not found");
            }
            if (! userRepository.existsById(bookingRequestDTO.getUserId())){
                throw new UserNotFoundException("User not found");
            }

        Booking booking = new Booking();
        booking.setListingId(bookingRequestDTO.getListingId());
        booking.setUserId(bookingRequestDTO.getUserId());
        booking.setStatus(Status.PENDING);
        booking.setCreatedDate(LocalDateTime.now());
        booking.setAcceptedByHost(false);

        Booking savedBooking = bookingRepository.save(booking);


        return new BookingRequestResponse(savedBooking.getBookingId(), "Booking successfully created",booking.getStatus());

    }


    // Checks to see if the host has accepted the booking before we can create a booking
    // If the host declined or has not yet accepted the booking we return false, otherwise we return true
    public void confirmBooking(BookingRequestDTO bookingRequestDTO) {

        boolean isConfirmed = bookingRequestDTO.isAcceptedByHost();

        if (isConfirmed) {
           bookingRequestDTO.setStatus(Status.BOOKED);
           bookingRequestDTO.setAcceptedByHost(true);
        }
    }
    // Calculates the price by taking the listings price and multiplication it by the days a user wants to book
    // After im adding the fee which is 5%
    public void calculatePrice(@Valid BookingRequestDTO bookingRequestDTO, Listing listing, Availability availability) {

        long numberOfDays = ChronoUnit.DAYS.between(availability.getStartDate(), availability.getEndDate());

        double totalPrice = listing.getPricePerNight() * numberOfDays *1.05;

    }
}

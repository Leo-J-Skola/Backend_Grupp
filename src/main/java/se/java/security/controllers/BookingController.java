package se.java.security.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.java.security.dto.BookingRequest;
import se.java.security.dto.BookingResponse;
import se.java.security.models.*;
import se.java.security.repository.BookingRepository;
import se.java.security.services.BookingService;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;
    private final BookingRepository bookingRepository;
    @Autowired
    public BookingController(BookingService bookingService, BookingRepository bookingRepository) {
        this.bookingService = bookingService;
        this.bookingRepository = bookingRepository;
    }

    // create a booking object
    @PostMapping("/request")
    public ResponseEntity<?> createBooking(@Valid @RequestBody BookingRequest bookingRequest) {
        BookingResponse response = bookingService.sendBookingRequest(bookingRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // list all booking objects
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        // list all bookings
        List<Booking> booking = bookingRepository.findAll();
        // return
        return ResponseEntity.status(HttpStatus.OK).body(booking);
    }

    // list all booking objects by user id
    @GetMapping("/user-bookings/{userId}")
    public ResponseEntity<List<Booking>> getUserBookings(@PathVariable String userId) {
        // list all bookings
        List<Booking> bookings = bookingRepository.getAllBookingsByUserId(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No bookings found for user"));
        return ResponseEntity.ok(bookings);
    }

    // get specific booking
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getSpecificBooking(@PathVariable String id) {
        // check if booking id exists, or throw
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found"));

        // return values of the booking object
        return ResponseEntity.ok(booking);
    }

    // update booking object
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateBooking(@PathVariable String id) {
        // check if booking id exists, or throw
        Booking existingBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found"));
        // change booking details
        //booking confirmation not yet implemented correctly
//        bookingService.confirmBooking(existingBooking);


        // return values of the booking object
        return ResponseEntity.ok(bookingRepository.save(existingBooking));
    }

    // delete a booking object
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable String id) {
        // check if booking id exists, or throw
        if(!bookingRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found");
        }

        // delete the object
        bookingRepository.deleteById(id);

        // return no values
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/accept")
    public ResponseEntity<Booking> acceptBooking(@PathVariable String id) {
        Booking booking = bookingService.acceptBooking(id);
        return ResponseEntity.ok(booking);
    }

    @PostMapping("/{id}/decline")
    public ResponseEntity<Booking> declineBooking(@PathVariable String id) {
        Booking booking = bookingService.declineBooking(id);
        return ResponseEntity.ok(booking);
    }
}
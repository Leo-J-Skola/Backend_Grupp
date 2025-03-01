package se.java.security.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.java.security.models.Availability;
import se.java.security.models.Booking;
import se.java.security.models.Listing;
import se.java.security.repository.BookingRepository;
import se.java.security.services.BookingService;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;
    private final BookingRepository bookingRepository;

    public BookingController(BookingService bookingService, BookingRepository bookingRepository) {
        this.bookingService = bookingService;
        this.bookingRepository = bookingRepository;
    }

    // create a booking object
    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@RequestBody Booking booking, Listing listing, Availability availability) {
        if (!bookingService.confirmBooking(booking)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Booking was not confirmed");

        }else bookingService.calculatePrice(booking, listing, availability);
        booking = bookingRepository.save(booking);
        ResponseEntity.ok().body("Booking was created");
        return ResponseEntity.status(HttpStatus.CREATED).body(booking);
    }

    // list all booking objects
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        // list all bookings
        List<Booking> booking = bookingRepository.findAll();
        // return
        return ResponseEntity.status(HttpStatus.OK).body(booking);
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
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBooking(@PathVariable String id, @RequestBody Booking bookingDetails) {
        // check if booking id exists, or throw
        Booking existingBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found"));

        // change booking details
        existingBooking.setBookingId(bookingDetails.getBookingId());
        existingBooking.setUserID(bookingDetails.getUserID());
        existingBooking.setListingID(bookingDetails.getListingID());
        existingBooking.setStatus(bookingDetails.getStatus());
        existingBooking.setFee(bookingDetails.getFee());
        existingBooking.setLastModifiedDate(bookingDetails.getLastModifiedDate());
        existingBooking.setTotalAmount(bookingDetails.getTotalAmount());

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
}
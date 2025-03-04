package se.java.security.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.java.security.dto.BookingRequestDTO;
import se.java.security.dto.BookingRequestResponse;
import se.java.security.models.Availability;
import se.java.security.models.Booking;
import se.java.security.models.Status;
import se.java.security.models.User;
import se.java.security.repository.BookingRepository;
import se.java.security.services.BookingService;

import java.time.LocalDateTime;
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
    public ResponseEntity<BookingRequestResponse> createBooking(@Valid @RequestBody BookingRequestDTO bookingRequestDTO) {

       BookingRequestResponse response = bookingService.bookingRequest(bookingRequestDTO);

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
    public ResponseEntity<?> updateBooking(@PathVariable String id, @RequestBody BookingRequestDTO bookingRequestDTO) {
        // check if booking id exists, or throw
        Booking existingBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found"));
        bookingService.confirmBooking(bookingRequestDTO);

        // change booking details
        existingBooking.setBookingId(bookingRequestDTO.getBookingId());
        existingBooking.setUserId(bookingRequestDTO.getUserId());
        existingBooking.setListingId(bookingRequestDTO.getListingId());
        existingBooking.setStatus(bookingRequestDTO.getStatus());
        existingBooking.setFee(existingBooking.getFee());
        existingBooking.setLastModifiedDate(existingBooking.getLastModifiedDate());
        existingBooking.setTotalAmount(existingBooking.getTotalAmount());

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
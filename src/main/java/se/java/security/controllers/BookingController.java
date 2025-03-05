package se.java.security.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.java.security.dto.BookingDTO;
import se.java.security.dto.BookingResponse;
import se.java.security.models.*;
import se.java.security.repository.BookingRepository;
import se.java.security.services.BookingService;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<BookingResponse> createBooking(@Valid @RequestBody BookingDTO bookingDTO, Optional<Listing> listingOpt) {
        bookingService.convertBookingDTO(bookingDTO);
        BookingResponse response = bookingService.bookingRequest(bookingDTO);

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
    public ResponseEntity<?> updateBooking(@PathVariable String id, @RequestBody BookingDTO bookingDTO) {
        // check if booking id exists, or throw
        Booking existingBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found"));
        bookingService.confirmBooking(bookingDTO);

        // change booking details
        existingBooking.setBookingId(bookingDTO.getBookingId());
        existingBooking.setUserId(bookingDTO.getUserId());
        existingBooking.setListingId(bookingDTO.getListingId());
        existingBooking.setStatus(bookingDTO.getStatus());
        existingBooking.setFee(existingBooking.getFee());
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
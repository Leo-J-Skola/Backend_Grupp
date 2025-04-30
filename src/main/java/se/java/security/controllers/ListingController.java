package se.java.security.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.java.security.models.Listing;
import se.java.security.services.ListingService;
import org.springframework.security.access.prepost.PreAuthorize;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/listing")
public class ListingController {

    private final ListingService listingService;

    @Autowired

    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping
    public ResponseEntity<List<Listing>> getAllListings() {
        return listingService.getAllListings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSpecificListing(@PathVariable String id) {
        return listingService.getSpecificListing(id);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createListing(@Valid @RequestBody Listing listing, Date startDate, Date endDate, @RequestHeader("Authorization") String token) {
        return listingService.createListing(listing, startDate, endDate, token);

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateListing(@PathVariable String id, @RequestBody Listing updatedListing, @RequestHeader("Authorization") String token) {
        return listingService.updateListing(id, updatedListing, token);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteListing(@PathVariable String id, @RequestHeader("Authorization") String token) {
        return listingService.deleteListing(id, token);
    }

}
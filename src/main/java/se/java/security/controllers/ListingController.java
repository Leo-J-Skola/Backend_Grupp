package se.java.security.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.java.security.models.Listing;
import se.java.security.repository.ListingRepository;
import se.java.security.services.ListingService;

import java.util.List;

@RestController
@RequestMapping("/listing")
public class ListingController {
    private final ListingService listingService;
    private final ListingRepository listingRepository;

    public ListingController(ListingService listingService, ListingRepository listingRepository) {
        this.listingService = listingService;
        this.listingRepository = listingRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<Listing> createListing(@RequestBody Listing listing) {
        listingService.createListing(listing); //go to ListingService class to authenticate and validate before creating a listing
        listingRepository.save(listing);
        return ResponseEntity.status(HttpStatus.CREATED).body(listing);
    }

    // list all listing objects
    @GetMapping
    public ResponseEntity<List<Listing>> getAllListings() {
        // list all listings
        List<Listing> listing = listingRepository.findAll();

        // return values of all listing objects
        return ResponseEntity.ok(listing);
    }

    // get specific user
    @GetMapping("/{id}")
    public ResponseEntity<?> getSpecificListing(@PathVariable String id) {
        // check if listing id exists, or throw
        Listing listing = listingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Listing not found"));

        // return values of the listing object
        return ResponseEntity.ok(listing);
    }

    // update listing details
    @PutMapping("/{id}")
    public ResponseEntity<?> updateListing(@PathVariable String id, @Valid @RequestBody Listing listingDetails) {
        // check if listing id exists, or throw
        Listing existingListing = listingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Listing not found"));

        // change listing details
        existingListing.setTitle(listingDetails.getTitle());
        existingListing.setDescription(listingDetails.getDescription());
        existingListing.setRooms(listingDetails.getRooms());
        existingListing.setPricePerNight(listingDetails.getPricePerNight());
        existingListing.setLocation(listingDetails.getLocation());
        existingListing.setImageUrl(listingDetails.getImageUrl());

        // return values of the listing object
        return ResponseEntity.ok(listingRepository.save(existingListing));
    }

    // delete a listing object
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteListing(@PathVariable String id) {
        // check if listing id exists, or throw
        if(!listingRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Listing not found");
        }

        // delete the object
        listingRepository.deleteById(id);

        // return no values
        return ResponseEntity.noContent().build();
    }
}
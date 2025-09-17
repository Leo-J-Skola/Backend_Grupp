package se.java.security.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import se.java.security.models.Availability;
import se.java.security.models.Listing;
import se.java.security.models.User;
import se.java.security.repository.ListingRepository;
import se.java.security.repository.UserRepository;
import se.java.security.util.JwtUtil;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

import java.util.Optional;

@Service
public class ListingService {

    private final ListingRepository listingRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;



    public ListingService(ListingRepository listingRepository, UserRepository userRepository, JwtUtil jwtUtil) {
        this.listingRepository = listingRepository;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<List<Listing>> getAllListings() {
        List<Listing> listings = listingRepository.findAll();
        return ResponseEntity.ok(listings);
    }

    public ResponseEntity<?> getSpecificListing(String id) {
        Listing listing = listingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Listing not found"));
        return ResponseEntity.ok(listing);
    }

    public ResponseEntity<Listing> createListing(Listing listing, Date startDate, Date endDate, String token) {

        String username = extractUsernameFromToken(token);
        if (username == null) {
            return ResponseEntity.status(401).body(null);
        }

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User was not found"));
        listing.setHostId(user.getId());
        listing.getAvailability().add(new Availability(startDate, endDate, listingId));
        listing.setAvailability(listing.getAvailability());
        Listing savedListing = listingRepository.save(listing);

        return new ResponseEntity<>(savedListing, HttpStatus.CREATED);
    }

    public ResponseEntity<?> updateListing(String listingId, Listing updatedListing, String token) {
        String username = extractUsernameFromToken(token);
        if (username == null) {
            return ResponseEntity.status(401).body("Login expired, please log in again to update this listing");
        }

        Optional<Listing> existingListingOptional = listingRepository.findById(listingId);
        if (existingListingOptional.isEmpty()) {
            return ResponseEntity.status(404).body("Listing " + listingId + " was not found");
        }

        Listing existingListing = existingListingOptional.get();
        if (!existingListing.getHostId().equals(userRepository.findByUsername(username).get().getId())) {
            return ResponseEntity.status(401).body("You are not allowed to update this listing" + listingId);
        }

        existingListing.setTitle(updatedListing.getTitle());
        existingListing.setDescription(updatedListing.getDescription());
        existingListing.setRooms(updatedListing.getRooms());
        existingListing.setPricePerNight(updatedListing.getPricePerNight());
        existingListing.setLocation(updatedListing.getLocation());
        existingListing.setImageUrl(updatedListing.getImageUrl());

        listingRepository.save(existingListing);
        return new ResponseEntity<>(existingListing, HttpStatus.CREATED);
    }

    public ResponseEntity<?> deleteListing(String listingId, String token) {
        String username = extractUsernameFromToken(token);
        if (username == null) {
            return ResponseEntity.status(401).body("Login expired, please log in again to delete this listing");
        }

        Optional<Listing> existingListingOptional = listingRepository.findById(listingId);
        if (existingListingOptional.isEmpty()) {
            return ResponseEntity.status(404).body("Listing "  + listingId + " was not found");
        }

        Listing existingListing = existingListingOptional.get();
        if (!existingListing.getHostId().equals(userRepository.findByUsername(username).get().getId())) {
            return ResponseEntity.status(403).body("Not allowed to delete this listing");
        }

        listingRepository.delete(existingListing);
        return ResponseEntity.ok("Listing " + listingId + " deleted");
    }

    private String extractUsernameFromToken(String token) {
        try {
            return jwtUtil.extractUsername(token.substring(7));
        } catch (Exception e) {
            return null;
        }
    }
    public boolean removeAvailability(Availability availability, Listing listing) {
        return listing.getAvailability().remove(availability);
    }
}
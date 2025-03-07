package se.java.security.services;

import jakarta.validation.Valid;
import org.springframework.data.mongodb.core.mapping.Unwrapped;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import se.java.security.exceptions.ListingNotFoundException;
import se.java.security.exceptions.UserNotFoundException;
import se.java.security.models.Listing;
import se.java.security.models.Rating;
import se.java.security.repository.RatingRepository;
import se.java.security.repository.UserRepository;
import se.java.security.repository.ListingRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final UserRepository userRepository;
    private final ListingRepository listingRepository;

    public RatingService(RatingRepository ratingRepository, UserRepository userRepository, ListingRepository listingRepository) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.listingRepository = listingRepository;
    }

    // check if the user has created a booking and if the host has accepted, through bookingService?

    // a user cannot rate his own listing

    // Checking if the user is logged in

    public void rateListing(@Valid Rating rating) {
        /*String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        if (username == null) {
            throw new IllegalArgumentException("User is not logged in");
        }*/
         // user will be able to rate a listing, but only once per listing
         if (ratingRepository.findByUserIdAndListingId(rating.getUserId(), rating.getListingId()).isPresent()) {
             throw new IllegalArgumentException("You have already rated this listing");
         }
        if (! listingRepository.existsById(rating.getListingId())) {
            throw new ListingNotFoundException("Listing not found");
        }

        // Check if the user exists
        if (! userRepository.existsById(rating.getUserId())) {
            throw new UserNotFoundException("User not found");
        }
        // Try to retrieve the listing from the database
        Optional<Listing> optional = listingRepository.findById(rating.getListingId());
        Listing listing = listingRepository.findById(rating.getListingId())
                .orElseThrow(() -> new ListingNotFoundException("Listing not found"));

         // create a new rating with the values of rating
        rating.setUserId(rating.getUserId());
        rating.setListingId(rating.getListingId());
        rating.setCreatedAt(LocalDateTime.now());
        ratingRepository.save(rating);

    }

    // get the rating of a listing and divide it with all the users that has given it a rating
    public double getAverageRating(String listingId) { //Method to calculate average rating of a listing
        double ratingCount = ratingRepository.countRatingByListingId(listingId);
        Rating rating = new Rating();
        return ratingCount/rating.getRating();
        }
}

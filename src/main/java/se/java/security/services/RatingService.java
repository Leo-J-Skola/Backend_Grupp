package se.java.security.services;

import jakarta.validation.Valid;
import org.springframework.data.mongodb.core.mapping.Unwrapped;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import se.java.security.models.Rating;
import se.java.security.repository.RatingRepository;
import se.java.security.repository.UserRepository;
import se.java.security.repository.ListingRepository;

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

    // check if the user has created a booking and if the host has accepted
    // get the rating of a listing and divide it with all the users that has given it a rating

    // Checking if the user is logged in
    // user will be able to rate a listing, but only once per listing
    public void rateListing(@Valid Rating rating) {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        if (username == null) {
            throw new IllegalArgumentException("User is not logged in");
        }
         if (ratingRepository.findByUserIdAndListingId(username, rating.getListingId()).isPresent()) {
             throw new IllegalArgumentException("You have already rated this listing");
         }
         ratingRepository.save(rating);
    }

    public double getAverageRating(String listingId) { //Method to calculate average rating of a listing
        double ratingCount = ratingRepository.countRatingByListingId(listingId);
        Rating rating = new Rating();
        return ratingCount/rating.getRating();
        }
}

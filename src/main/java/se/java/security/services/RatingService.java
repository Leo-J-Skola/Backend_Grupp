package se.java.security.services;

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

    public void rateListing(Rating rating) { //this will let the user rate a listing, but can only rate a listing once
    }

    public double getAverageRating(String listingId) { //Method to calculate average rating of a listing
        return 0;
        }
}

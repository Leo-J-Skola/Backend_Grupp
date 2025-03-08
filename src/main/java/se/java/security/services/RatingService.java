package se.java.security.services;

import jakarta.validation.Valid;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Unwrapped;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import se.java.security.dto.BookingDTO;
import se.java.security.exceptions.ListingNotFoundException;
import se.java.security.exceptions.UserNotFoundException;
import se.java.security.models.Booking;
import se.java.security.models.Listing;
import se.java.security.models.Rating;
import se.java.security.repository.BookingRepository;
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
    private final BookingRepository bookingRepository;

    public RatingService(RatingRepository ratingRepository, UserRepository userRepository, ListingRepository listingRepository, BookingRepository bookingRepository) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.listingRepository = listingRepository;
        this.bookingRepository = bookingRepository;
    }

    // check if the user has created a booking and if the host has accepted, through bookingService?

    // a user cannot rate his own listing

    // Checking if the user is logged in

    // kolla objekt id från user mot user id och user id i booking

    // kolla username i user med username i booking



    public void rateListing(@Valid Rating rating) {
        /*String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();


        if (username == null) {
            throw new IllegalArgumentException("User is not logged in");
        }
         // user will be able to rate a listing, but only once per listing
         if (ratingRepository.findByUserIdAndListingId(rating.getUserId(), rating.getListingId()).isPresent()) {
             throw new IllegalArgumentException("You have already rated this listing");
         }*/

        // Check if the user exists
        if (! userRepository.existsById(rating.getUserId())) {
            throw new UserNotFoundException("User not found");
        }
        // Try to retrieve the listing from the database
        Optional<Listing> optional = listingRepository.findById(rating.getListingId());
        Listing listing = listingRepository.findById(rating.getListingId())
                .orElseThrow(() -> new ListingNotFoundException("Listing not found"));

        // check if user is not the owner of the listing
        Optional<Listing> host = listingRepository.findById(listing.getUserId());

        Optional<BookingDTO> userIdAndListingId = bookingRepository.findByUserIdAndListingId(rating.getUserId(), rating.getListingId());


        if (host.equals.to rating.getUserId()) {

            // create a new rating with the values of rating
            rating.setUserId();
            rating.setListingId(listing.getId());
            ratingRepository.save(rating);
        }


       /*  // create a new rating with the values of rating
        rating.setUserId();
        rating.setListingId(listing.getId());
        rating.setCreatedAt(LocalDateTime.now());
        ratingRepository.save(rating);*
        */

    }

    // get the rating of a listing and divide it with all the users that has given it a rating
    public double getAverageRating(String listingId) { //Method to calculate average rating of a listing
        double ratingCount = ratingRepository.countRatingByListingId(listingId);
        Rating rating = new Rating();
        return ratingCount/rating.getRating();
        }


}

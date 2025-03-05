package se.java.security.services;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import se.java.security.models.Listing;
import se.java.security.repository.ListingRepository;

@Service
public class ListingService {

    private final ListingRepository listingRepository;
    private final UserService userService;
    private final RatingService ratingService;

    public ListingService(ListingRepository listingRepository, UserService userService, RatingService ratingService) {
        this.listingRepository = listingRepository;
        this.userService = userService;
        this.ratingService = ratingService;
    }

    //First we get the username from the token (to see if the user is logged in)
    //Then we check if the listing is valid (title, description, rooms, price per night, location, image url)
    //Then we save the listing with their username we got from the token
    public void createListing(Listing listing) {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        if (username == null) {
            throw new IllegalArgumentException("User is not logged in");
        }
        if (listing.getTitle() == null ||
                listing.getTitle().isEmpty() ||
                listing.getDescription() == null ||
                listing.getDescription().isEmpty() ||
                listing.getRooms() == null ||
                //listing.getPricePerNight() == null ||
                listing.getLocation() == null ||
                listing.getImageUrl() == null) {
            throw new IllegalArgumentException("All fields are required");
        }
                listing.setUsername(username);
                listing.setRating(0);
                listing.setImageUrl(listing.getImageUrl());
                listing.setDescription(listing.getDescription());
                listing.setTitle(listing.getTitle());
               // listing.setPricePerNight(listing.getPricePerNight());
    }
}
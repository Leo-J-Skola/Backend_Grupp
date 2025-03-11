package se.java.security.services;

import jakarta.validation.Valid;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Unwrapped;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import se.java.security.exceptions.BookingUnavailableException;
import se.java.security.exceptions.ListingNotFoundException;
import se.java.security.exceptions.UnauthorizedException;
import se.java.security.exceptions.UserNotFoundException;
import se.java.security.models.*;
import se.java.security.repository.BookingRepository;
import se.java.security.repository.RatingRepository;
import se.java.security.repository.UserRepository;
import se.java.security.repository.ListingRepository;

import java.time.LocalDateTime;
import java.util.Objects;
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

    //NÄR MAN ÄNDRAR STATUS PÅ BOKNING SÅ SKAPAS EN NY BOKNING

    // Att fixa: det går ej att lägga rating om en användare har flera bokningar på samma listing(PROBLEMET ÄR att det blir en konflikt eftersom
    // det skapas en dubblett av bokning när hosten ändrar status

    // rad 80 fungerar ej, behöver fixas så att man ej kan ratea en listing en gång

    // check if the user has created a booking and if the host has accepted, through bookingService?

    // a user cannot rate his own listing

    // Checking if the user is logged in

    // kolla objekt id från user mot user id och user id i booking

    // kolla userid och listingId i bookingDTO mot userId och listing i rating



    public void rateListing(Rating rating) {

        // check if user is logged in and has authetication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            throw new UnauthorizedException("User is not authenticated");
        }

        // if the user has authentication we gather his info through his username
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // checking if the listing exists
      Listing existinglisting = listingRepository.findById(rating.getListingId())
              .orElseThrow(() -> new ListingNotFoundException("Listing not found"));

        // check if user is the owner of the listing
      Listing host = listingRepository.findByUsernameAndId(user.getUsername(), rating.getListingId());
              if (host != null) {
                  throw new RuntimeException("You cannot rate your own listing");
              }

        // check if the user has a booking
        Booking booking = bookingRepository.findByUserIdAndListingId(user.getId(), rating.getListingId())
                .orElseThrow(() -> new ListingNotFoundException("User doesn´t have a booking"));

        //  you can only rate if the booking has status booked
        Status status = booking.getStatus();
        { if (status != Status.BOOKED)
            throw new BookingUnavailableException("You can´t rate a listing if it hasn´t been booked");
        }


        // user will be able to rate a listing, but only once per listing
        Optional<Rating> rateOnce = ratingRepository.findByUserIdAndListingId(user.getId(), rating.getListingId());
        if (rateOnce.isPresent()){
            throw new RuntimeException("You cannot rate the same listing more than once");
        }






        Rating newRating = new Rating();
        newRating.setUserId(user.getId());
        newRating.setListingId(rating.getListingId());
        newRating.setRating(rating.getRating());

        ratingRepository.save(newRating);
    }









  /*  // get the average rating of a listing by using count to divide the rating with all the users that has given it a rating
    public double getAverageRating(String listingId) {
        double ratingCount = ratingRepository.countRatingByListingId(listingId);

        return ratingCount/getRating();
        }
*/

}

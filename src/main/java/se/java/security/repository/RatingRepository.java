package se.java.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import se.java.security.models.Rating;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {
    Optional<Rating> findByUserIdAndListingId(String id, String listingId); //check if a user has already rated a listing
    List<Rating> findByListingId(String listingId);                //get the average rating of a listing
    double countRatingByListingId(String listingId);

    double getRatingByListingId(String listingId);

    Optional<Rating> findByUserIdAndListingIdAndBookingId(String id, String listingId, String bookingId);
}

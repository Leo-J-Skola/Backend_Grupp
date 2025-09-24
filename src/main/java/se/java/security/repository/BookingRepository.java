package se.java.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import se.java.security.models.Booking;
import se.java.security.models.BookingStatus;
import se.java.security.models.Listing;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {
    Optional<Booking> findById(String id);
    Optional<List<Booking>> getAllBookingsByUserId(String userId);
    Optional<Object> findByUserIdAndListingIdAndId(String id, String listingId, String bookingId);
    List<Booking> findByBookingStatus(BookingStatus bookingStatus);
    List<Booking> findByBookingStatusIn(List<BookingStatus> bookingStatuses);
    String listingId(Listing listingId);
    List<Booking> findByListingIdAndBookingStatusIn(Listing listing, List<BookingStatus> bookingStatus);
}

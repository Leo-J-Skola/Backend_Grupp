package se.java.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import se.java.security.models.Booking;
import se.java.security.models.BookingStatus;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {
    Optional<Booking> findById(String id);
    Optional<List<Booking>> getAllBookingsByUserId(String userId);
    Optional<Object> findByUserIdAndListingIdAndId(String id, String listingId, String bookingId);
    List<Booking> findByDates(String listingId, BookingStatus bookingStatus, Date endDate, Date startDate);
}

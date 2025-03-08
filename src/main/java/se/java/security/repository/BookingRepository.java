package se.java.security.repository;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import se.java.security.dto.BookingDTO;
import se.java.security.models.Booking;

import java.util.Optional;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {
    Optional<Booking> findById(String id);
    Optional<BookingDTO> findByUserIdAndListingId(String userId, String listingId);
}

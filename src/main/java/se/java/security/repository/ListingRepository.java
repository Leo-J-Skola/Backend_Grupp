package se.java.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import se.java.security.models.Listing;

import java.util.Optional;

@Repository
public interface ListingRepository extends MongoRepository<Listing, String> {
    Optional<Listing> findById(String id);

}


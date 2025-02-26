package se.java.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import se.java.security.models.Report;



@Repository
public interface ReportRepository extends MongoRepository<Report, String> {
    long countByUserId(String UserId);
}
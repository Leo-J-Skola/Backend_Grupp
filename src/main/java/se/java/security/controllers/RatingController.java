package se.java.security.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.java.security.models.Rating;
import se.java.security.repository.RatingRepository;
import se.java.security.repository.UserRepository;
import se.java.security.services.RatingService;
import se.java.security.services.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rating")
public class RatingController {

    private final RatingService ratingService;
    private final RatingRepository ratingRepository;

    public RatingController(RatingService ratingService, RatingRepository ratingRepository) {
        this.ratingService = ratingService;
        this.ratingRepository = ratingRepository;
    }

    @PostMapping("/rate") //Rate a listing
    public ResponseEntity<Rating> rateListing(@Valid @RequestBody Rating rating) {
        // try {
        ratingService.rateListing(rating);
        return ResponseEntity.status(HttpStatus.OK).body(rating);
       }

    @GetMapping("/average/{listingId}") //Get average rating of a listing
    public ResponseEntity<?> getAverageRating(@PathVariable String listingId) {
        //try {
            Double averageRating = ratingService.getAverageRating(listingId);
            return ResponseEntity.ok(averageRating);
       /* } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error. Couldn't get average rating", e);
        }*/
    }
}
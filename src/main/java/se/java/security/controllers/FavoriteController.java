package se.java.security.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.java.security.dto.FavoriteDTO;
import se.java.security.dto.FavoriteResponse;
import se.java.security.exceptions.ResourceNotFoundException;
import se.java.security.models.Favorite;
import se.java.security.repository.FavoriteRepository;
import se.java.security.services.FavoriteService;

import java.util.List;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    private final FavoriteService favoriteService;
    private final FavoriteRepository favoriteRepository;

    public FavoriteController(FavoriteService favoriteService, FavoriteRepository favoriteRepository) {
        this.favoriteService = favoriteService;
        this.favoriteRepository = favoriteRepository;
    }

    // create a favorite object
    @PostMapping("/create")
    public ResponseEntity<Favorite> createFavorite(@RequestBody FavoriteDTO favoriteDTO) {
        Favorite newFavorite = favoriteService.createFavorite(favoriteDTO);
        return new ResponseEntity<>(newFavorite, HttpStatus.CREATED);
    }

    // get all favorite objects
    //MÅSTE FIXA RETURNERA BARA RESPONSE EGENSKAPER
    @GetMapping("/all")
    public ResponseEntity<?> getAllFavorites() {
        return ResponseEntity.ok(favoriteService.getAllFavorites());
    }

    // get a users favorites objects
    @GetMapping("/user-favorites/{userId}")
    public ResponseEntity<?> getUserFavorites(@PathVariable String userId) {
        List<FavoriteResponse> favorites = favoriteService.getUserFavorites(userId);
        return ResponseEntity.ok(favorites);
    }

    @GetMapping("/specific-favorite/{id}")
    public ResponseEntity<Favorite> getSpecificFavorite(@PathVariable String id) {
        Favorite favorite = favoriteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Favorite not found with id: " + id));
        return ResponseEntity.ok(favorite);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFavorite(@PathVariable String id) {
        favoriteService.deleteFavorite(id);
        return ResponseEntity.ok("Favorite deleted");
    }
}
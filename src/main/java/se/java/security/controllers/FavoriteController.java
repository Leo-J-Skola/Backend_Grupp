package se.java.security.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.java.security.dto.FavoriteDTO;
import se.java.security.dto.FavoriteResponse;
import se.java.security.models.Favorite;
import se.java.security.services.FavoriteService;

import java.util.List;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    // create a new favorite object
    @PostMapping("/create")
    public ResponseEntity<Favorite> createFavorite(@RequestBody FavoriteDTO favoriteDTO) {
        Favorite newFavorite = favoriteService.createFavorite(favoriteDTO);
        return new ResponseEntity<>(newFavorite, HttpStatus.CREATED);
    }

    // get all favorite objects
    @GetMapping("/all")
    public ResponseEntity<?> getAllFavorites() {
        return ResponseEntity.ok(favoriteService.getAllFavorites());
    }

    // check all favorite objects linked to a user
    @GetMapping("/user-favorites/{userId}")
    public ResponseEntity<?> getUserFavorites(@PathVariable String userId) {
        List<FavoriteResponse> favorites = favoriteService.getUserFavorites(userId);
        return ResponseEntity.ok(favorites);
    }

    // get a specific favorite object
    @GetMapping("/specific-favorite/{id}")
    public ResponseEntity<?> getSpecificFavorite(@PathVariable String id) {
        return ResponseEntity.ok(favoriteService.getSpecificFavorite(id));
    }

    // delete a favorite object
    // message to confirm that the object has been removed
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFavorite(@PathVariable String id) {
        favoriteService.deleteFavorite(id);
        return ResponseEntity.ok("Favorite deleted");
    }
}
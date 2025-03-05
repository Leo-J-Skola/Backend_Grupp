package se.java.security.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.java.security.models.Favorite;
import se.java.security.repository.FavoriteRepository;

import java.util.List;
/*
@RestController
@RequestMapping("/favorite")
public class FavoriteController {
    private final FavoriteService favoriteService;
    private final FavoriteRepository favoriteRepository;

    public FavoriteController(FavoriteService favoriteService, FavoriteRepository favoriteRepository) {
        this.favoriteService = favoriteService;
        this.favoriteRepository = favoriteRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<Favorite> createFavorite(@RequestBody Favorite favorite) {
        //go to FavoriteService class to authenticate and validate before creating a favorite
        favoriteService.createFavorite(favorite);
        favoriteRepository.save(favorite);
        return ResponseEntity.status(HttpStatus.CREATED).body(favorite);
    }

    // list all favorite objects
    @GetMapping
    public ResponseEntity<List<Favorite>> getAllFavorites() {
        // list all favorites
        List<Favorite> favorite = favoriteRepository.findAll();

        // return values of all favorite objects
        return ResponseEntity.ok(favorite);
    }

    // get specific user object
    @GetMapping("/{id}")
    public ResponseEntity<?> getSpecificFavorite(@PathVariable String id) {
        // check if favorite id exists, or throw
        Favorite favorite = favoriteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Favorite not found"));

        // return values of the favorite object
        return ResponseEntity.ok(favorite);
    }

    // delete a favorite object
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFavorite(@PathVariable String id) {
        // check if favorite id exists, or throw
        if(!favoriteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Favorite not found");
        }

        // delete the object
        favoriteRepository.deleteById(id);

        // return no values
        return ResponseEntity.noContent().build();
    }
}*/
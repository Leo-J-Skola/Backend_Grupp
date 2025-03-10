package se.java.security.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.java.security.dto.FavoriteDTO;
import se.java.security.dto.FavoriteResponse;
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

    @PostMapping("/create")
    public ResponseEntity<Favorite> createFavorite(@RequestBody FavoriteDTO favoriteDTO) {
        Favorite newFavorite = favoriteService.createFavorite(favoriteDTO);
        return new ResponseEntity<>(newFavorite, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FavoriteResponse>> getAllFavorites() {
        List<FavoriteResponse> favorites = favoriteService.getAllFavorites();
        return ResponseEntity.ok(favorites);
    }

    @GetMapping("/user{userId}")
    public ResponseEntity<List<FavoriteResponse>> getUserFavorites(@PathVariable String userId) {
        List<FavoriteResponse> favorites = favoriteService.getUserFavorites(userId);
        return ResponseEntity.ok(favorites);
    }

    //@DeleteMapping("/delete")
    //public ResponseEntity<Favorite>

}
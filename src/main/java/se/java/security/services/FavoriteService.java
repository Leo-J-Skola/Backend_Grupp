package se.java.security.services;

import org.springframework.stereotype.Service;
import se.java.security.exceptions.ResourceNotFoundException;
import se.java.security.models.Favorite;
import se.java.security.repository.FavoriteRepository;
import se.java.security.repository.ListingRepository;
import se.java.security.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final ListingRepository listingRepository;
    private final ListingService listingService;
    private final UserService userService;


    public FavoriteService(FavoriteRepository favoriteRepository, UserRepository userRepository, ListingRepository listingRepository, ListingService listingService, UserService userService) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
        this.listingRepository = listingRepository;
        this.listingService = listingService;
        this.userService = userService;
    }

    public Favorite createFavorite(Favorite favorite) {

        // check if a user object has already favorited a listing object
        //if () {
//
        //}

        // Return
        return favoriteRepository.save(favorite);
    }

    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAll();
    }

    public Optional<Favorite> getFavoriteById(String id) {
        return favoriteRepository.findById(id);
    }

    public void deleteFavorite(String id) {
        Favorite favorite = favoriteRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("Favorite not found with id: " + id));
        favoriteRepository.deleteById(id);
    }
}
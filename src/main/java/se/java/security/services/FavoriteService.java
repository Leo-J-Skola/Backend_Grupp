package se.java.security.services;

import org.springframework.stereotype.Service;
import se.java.security.dto.FavoriteDTO;
import se.java.security.exceptions.ResourceNotFoundException;
import se.java.security.models.Favorite;
import se.java.security.models.Listing;
import se.java.security.models.User;
import se.java.security.repository.FavoriteRepository;
import se.java.security.repository.ListingRepository;
import se.java.security.repository.UserRepository;

import java.util.*;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final ListingRepository listingRepository;

    public FavoriteService(FavoriteRepository favoriteRepository, UserRepository userRepository, ListingRepository listingRepository) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
        this.listingRepository = listingRepository;
    }

    // create a new favorite object
    public Favorite createFavorite(FavoriteDTO favoriteDTO) {
        User user = userRepository.findById(favoriteDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Listing listing = listingRepository.findById(favoriteDTO.getListingId())
                .orElseThrow(() -> new ResourceNotFoundException("Listing not found"));

        // empty list
        List<Favorite> favorites = new ArrayList<>();
        Map<String, Integer> quantities = new HashMap<>();

        Favorite newFavorite = new Favorite();
        newFavorite.setUserId(user);
        newFavorite.setHostId(user);
        newFavorite.setListingId(listing);

        return favoriteRepository.save(newFavorite);
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
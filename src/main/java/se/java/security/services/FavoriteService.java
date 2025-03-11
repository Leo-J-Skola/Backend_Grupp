package se.java.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.java.security.dto.FavoriteDTO;
import se.java.security.dto.FavoriteResponse;
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

    @Autowired
    private final FavoriteRepository favoriteRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final ListingRepository listingRepository;

    public FavoriteService(FavoriteRepository favoriteRepository, UserRepository userRepository, ListingRepository listingRepository) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
        this.listingRepository = listingRepository;
    }

    private static List<FavoriteResponse> getFavoriteResponses(List<Favorite> favorites) {
        List<FavoriteResponse> favoriteResponses = favorites.stream()
                .map(fav -> new FavoriteResponse(
                        fav.getUserId().getUsername(),
                        fav.getUserId().getEmail(),
                        fav.getListingId().getTitle(),
                        fav.getListingId().getDescription()))
                        .toList();

        return favoriteResponses;
    }

    public Favorite createFavorite(FavoriteDTO favoriteDTO) {
        User user = userRepository.findById(favoriteDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Listing listing = listingRepository.findById(favoriteDTO.getListingId())
                .orElseThrow(() -> new ResourceNotFoundException("Listing not found"));

        Favorite newFavorite = new Favorite();
        newFavorite.setUserId(user);
        newFavorite.setListingId(listing);

        return favoriteRepository.save(newFavorite);
    }

    public List<FavoriteResponse> getAllFavorites() {
        // check that there are favorite objects present
        if (favoriteRepository.findAll().isEmpty()) {
            throw new ResourceNotFoundException("No favorites found");
        }
        // finds all favorite objects
        List<Favorite> favorites = favoriteRepository.findAll();
        // only returns username, email and listingid using favoriteresponse
        return getFavoriteResponses(favorites);
    }

    // get a users favorited objects
    public List<FavoriteResponse> getUserFavorites(String userId) {
        // check that the user id exists
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }

        List<Favorite> favorites = favoriteRepository.findFavoritesByUserId_Id(userId);

        return getFavoriteResponses(favorites);
    }

    public List<FavoriteResponse> getSpecificFavorite(String id) {
        List<Favorite> favorites = new ArrayList<>();
        favorites.add(favoriteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Favorite not found with id: " + id)));
        return getFavoriteResponses(favorites);
    }

    // This method deletes a favorite object
    // validates that there is an existing favorite object id, or it will throw a message
    public void deleteFavorite(String id) {
        if (!favoriteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Favorite not found with id: " + id);
        }
        favoriteRepository.deleteById(id);
    }
}
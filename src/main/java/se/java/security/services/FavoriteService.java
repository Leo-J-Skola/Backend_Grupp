package se.java.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import java.util.stream.Collectors;

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

    private FavoriteResponse convertToDTO(Favorite favorite) {
        FavoriteResponse favoriteResponse = new FavoriteResponse();

        favoriteResponse.setUsername(favorite.getUserId().getId());
        favoriteResponse.setEmail(favorite.getUserId().getEmail());

        favoriteResponse.getFavoritedListingsIds().add(favorite.getListingId().getId());

        return favoriteResponse;
    }

    private static List<FavoriteResponse> getFavoriteResponses(List<Favorite> favorites) {
        List<FavoriteResponse> favoriteResponses = favorites.stream()
                .map(fav -> new FavoriteResponse(fav.getUserId().getUsername(),
                        fav.getUserId().getEmail(),
                        Collections.singletonList(fav.getListingId().getId())))
                .toList();
        return favoriteResponses;
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
        newFavorite.setListingId(listing);

        return favoriteRepository.save(newFavorite);
    }

    //public favorite response
    public List<FavoriteResponse> getAllFavorites() {
        // check that there are favorite objects present
        if (favoriteRepository.findAll().isEmpty()) {
            throw new ResourceNotFoundException("No favorites found");
        }

        List<Favorite> favorites = favoriteRepository.findAll();

        List<FavoriteResponse> favoriteResponses = getFavoriteResponses(favorites);

        return favoriteResponses;
    }

    public List<FavoriteResponse> getUserFavorites(String userId) {
        // check that the user id exists
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found");
        }

        List<Favorite> favorites = favoriteRepository.findFavoritesByUserId_Id(userId);

        List<FavoriteResponse> favoriteResponses = getFavoriteResponses(favorites);

        return favoriteResponses;
    }

    public Optional<Favorite> getSpecificFavorite(String id) {
        if(!favoriteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Favorite not found");
        }
        return favoriteRepository.findById(id);
    }

    public void deleteFavorite(String id) {
        if (!favoriteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Favorite not found with id: " + id);
        }
        favoriteRepository.deleteById(id);
    }
}
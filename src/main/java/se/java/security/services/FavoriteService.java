package se.java.security.services;

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

    private final FavoriteRepository favoriteRepository;
    private final UserRepository userRepository;
    private final ListingRepository listingRepository;

    public FavoriteService(FavoriteRepository favoriteRepository, UserRepository userRepository, ListingRepository listingRepository) {
        this.favoriteRepository = favoriteRepository;
        this.userRepository = userRepository;
        this.listingRepository = listingRepository;
    }

    private FavoriteResponse convertToDTO(Favorite favorite) {
        FavoriteResponse favoriteResponse = new FavoriteResponse();

        favoriteResponse.setUserId(favorite.getUserId().getId());

        favoriteResponse.setFavoritedListingsIds(
                favorite.getFavorites().stream()
                        .map(Favorite::getId)
                        .collect(Collectors.toList())
        );
        return favoriteResponse;
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

    public List<FavoriteResponse> getAllFavorites() {
        List<Favorite> favorites = favoriteRepository.findAll();

        // convert the favorite objects to favoriteResponse objects
        return favorites.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<FavoriteResponse> getUserFavorites(String userId) {
        if(!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found");
        }

        // OBS
        // DENNA SKA VA List<Favorite> men blev error!
        Optional<Favorite> favorites = favoriteRepository.findByUserId(userId);

        return favorites.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    //public Optional<Favorite> getFavoriteById(String id) {
    //    return favoriteRepository.findById(id);
    //}
//
    //public void deleteFavorite(String id) {
    //    Favorite favorite = favoriteRepository.findById(id)
    //                    .orElseThrow(() -> new ResourceNotFoundException("Favorite not found with id: " + id));
    //    favoriteRepository.deleteById(id);
    //}
}
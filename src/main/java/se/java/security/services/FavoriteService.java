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

    // this method will convert a list of FavoriteResponse objects to filter what will be shown
    // uses stream to map the properties of user and listing correctly
    // toList will gather the converted properties and add it to a new object of FavoriteResponse
    // returns the new object
    private List<FavoriteResponse> getFavoriteResponses(List<Favorite> favorites) {
        List<FavoriteResponse> favoriteResponses = favorites.stream()
                .map(fav -> new FavoriteResponse(
                        fav.getUserId().getUsername(),
                        fav.getUserId().getEmail(),
                        fav.getListingId().getTitle(),
                        fav.getListingId().getDescription()))
                        .toList();
        return favoriteResponses;
    }

    // this method will create a new favorite object
    // validates that userId and listingId are existing, or throws a message
    // instantiates Favorite as newFavorite to fill in object id's of users and listings using setters
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

    // this method will get all favorite objects
    // validates that there are any favorite objects present, or throws a message
    // uses findAll to find all favorite objects
    // uses getFavoriteResponses to filter what will be shown
    public List<FavoriteResponse> getAllFavorites() {
        if (favoriteRepository.findAll().isEmpty()) {
            throw new ResourceNotFoundException("No favorites found");
        }
        List<Favorite> favorites = favoriteRepository.findAll();
        return getFavoriteResponses(favorites);
    }

    // this method finds all favorite objects linked to a user
    // validates that the user object exists using object id, or throws a message
    // filters out all favorite objects not linked to a user id, using findFavoritesByUserId_Id
    // uses getFavoriteResponses to filter what will be shown
    public List<FavoriteResponse> getUserFavorites(String userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
        List<Favorite> favorites = favoriteRepository.findFavoritesByUserId_Id(userId);
        return getFavoriteResponses(favorites);
    }

    // this method finds a specific favorite object
    // instantiates an arrayList, then adds the object to the arraylist using id to find it
    // validates that the user object exists using object id, or throws a message
    // uses getFavoritesResponses method to filter what will be shown
    public List<FavoriteResponse> getSpecificFavorite(String id) {
        List<Favorite> favorites = new ArrayList<>();
        favorites.add(favoriteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Favorite not found with id: " + id)));
        return getFavoriteResponses(favorites);
    }

    // this method deletes a favorite object
    // validates that the user object exists using object id, or throws a message
    // deletes the favorite object using object id
    public void deleteFavorite(String id) {
        if (!favoriteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Favorite not found with id: " + id);
        }
        favoriteRepository.deleteById(id);
    }
}
package se.java.security.services;

import org.springframework.stereotype.Service;
import se.java.security.dto.FavoriteDTO;
import se.java.security.models.Favorite;
import se.java.security.repository.FavoriteRepository;
import se.java.security.repository.ListingRepository;
import se.java.security.repository.UserRepository;

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

    public Favorite createFavorite(FavoriteDTO FavoriteDTO) {
        // instantiate object
        Favorite favorite = new Favorite();

        // check if a user object has already favorited a listing object
        if () {

        }

        // Return
        return favoriteRepository.save(favorite);
    }
}
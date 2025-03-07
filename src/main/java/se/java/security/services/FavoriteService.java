package se.java.security.services;

import org.springframework.stereotype.Service;
import se.java.security.models.Favorite;
import se.java.security.repository.FavoriteRepository;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final ListingService listingService;
    private final UserService userService;


    public FavoriteService(FavoriteRepository favoriteRepository, ListingService listingService, UserService userService) {
        this.favoriteRepository = favoriteRepository;
        this.listingService = listingService;
        this.userService = userService;
    }

    public void createFavorite(Favorite favorite) {

    }
}

package se.java.security.services;

import org.springframework.stereotype.Service;
import se.java.security.repository.ListingRepository;

@Service
public class ListingService {

    private final ListingService listingService;
    private final ListingRepository listingRepository;

    public ListingService(ListingService listingService, ListingRepository listingRepository) {
        this.listingService = listingService;
        this.listingRepository = listingRepository;
    }
}

package se.java.security.dto;

import java.util.List;

public class FavoriteResponse {

    private String userId;
    private List<String> favoritedListingsIds;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setFavoritedListingsIds(List<String> favoritedListingsIds) {
        this.favoritedListingsIds = favoritedListingsIds;
    }

    public FavoriteResponse(List<String> favoritedListingsIds, String userId) {
        this.favoritedListingsIds = favoritedListingsIds;
        this.userId = userId;
    }

    public FavoriteResponse() {
    }
}

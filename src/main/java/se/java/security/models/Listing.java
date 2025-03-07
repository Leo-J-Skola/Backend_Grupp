package se.java.security.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "listings")
public class Listing {

    @Id
    @NotBlank
    private String id;
    private String username;
    @NotBlank(message = "Title can not be empty")
    private String title;
    @NotBlank(message = "Description can not be empty")
    private String description;
    @NotNull(message = "Amount of rooms cannot be empty")
    private Integer rooms;
    @NotNull(message = "Please enter a valid price")
    @Valid
    private Double pricePerNight;

    private Object location;
    @NotEmpty(message = "Photo cannot be empty")
    private String imageUrl;

    private Set<Availability> availability;
    private double rating;

    public Listing(String id, String title, String description, Integer rooms, Double pricePerNight, Object location, String imageUrl, Set<Availability> availability) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.rooms = rooms;
        this.pricePerNight = pricePerNight;
        this.location = location;
        this.imageUrl = imageUrl;
        this.availability = availability;
    }

    public Listing() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public @NotBlank(message = "Title can not be empty") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Title can not be empty") String title) {
        this.title = title;
    }

    public @NotBlank(message = "Description can not be empty") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "Description can not be empty") String description) {
        this.description = description;
    }

    public @NotNull(message = "Amount of rooms cannot be empty") Integer getRooms() {
        return rooms;
    }

    public void setRooms(@NotNull(message = "Amount of rooms cannot be empty") Integer rooms) {
        this.rooms = rooms;
    }

    @NotNull(message = "Please enter a valid price")
    public Double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(@NotNull(message = "Please enter a valid price") Double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public @NotBlank(message = "Photo cannot be empty") String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(@NotBlank(message = "Photo cannot be empty") String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<Availability> getAvailability() {
        return availability;
    }

    public void setAvailability(Set<Availability> availability) {
        this.availability = availability;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

package se.java.security.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "listings")
public class Listing {

    @Id
    private String id;
    @NotEmpty(message = "Title can not be empty")
    private String title;
    @NotEmpty(message = "Description can not be empty")
    private String description;
    @NotEmpty(message = "Amount of rooms cannot be empty")
    private Integer rooms;
    @NotEmpty(message = "Please enter a valid price")
    private double pricePerNight;

    private Object location;
    @NotEmpty(message = "Photo cannot be empty")
    private String imageUrl;

    private Set<Availability> availability;

    public Listing(String id, String title, String description, Integer rooms, double pricePerNight, Object location, String imageUrl, Set<Availability> availability) {
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

    public @NotEmpty(message = "Title can not be empty") String getTitle() {
        return title;
    }

    public void setTitle(@NotEmpty(message = "Title can not be empty") String title) {
        this.title = title;
    }

    public @NotEmpty(message = "Description can not be empty") String getDescription() {
        return description;
    }

    public void setDescription(@NotEmpty(message = "Description can not be empty") String description) {
        this.description = description;
    }

    public @NotEmpty(message = "Amount of rooms cannot be empty") Integer getRooms() {
        return rooms;
    }

    public void setRooms(@NotEmpty(message = "Amount of rooms cannot be empty") Integer rooms) {
        this.rooms = rooms;
    }

    @NotEmpty(message = "Please enter a valid price")
    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(@NotEmpty(message = "Please enter a valid price") double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        this.location = location;
    }

    public @NotEmpty(message = "Photo cannot be empty") String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(@NotEmpty(message = "Photo cannot be empty") String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<Availability> getAvailability() {
        return availability;
    }

    public void setAvailability(Set<Availability> availability) {
        this.availability = availability;
    }
}

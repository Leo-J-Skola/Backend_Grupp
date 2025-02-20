package se.java.security.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reports")
public class Report {
    @Id
    private String id;

    @NotBlank(message = "User id can not be empty")
    private String user_id;

    @NotBlank(message = "Listing id can not be empty")
    private String listing_id;

    @NotBlank(message = "Host id can not be empty")
    private String host;

    @NotBlank
    @Max(value = 100, message = "Description max characters exceeded")
    private String description;

    @NotBlank(message = "No photo has been applied")
    private String photo;

    public Report(String id, String user_id, String listing_id, String host, String description, String photo) {
        this.id = id;
        this.user_id = user_id;
        this.listing_id = listing_id;
        this.host = host;
        this.description = description;
        this.photo = photo;
    }

    public Report() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public @NotBlank(message = "User id can not be empty") String getUser_id() {
        return user_id;
    }

    public void setUser_id(@NotBlank(message = "User id can not be empty") String user_id) {
        this.user_id = user_id;
    }

    public @NotBlank(message = "Listing id can not be empty") String getListing_id() {
        return listing_id;
    }

    public void setListing_id(@NotBlank(message = "Listing id can not be empty") String listing_id) {
        this.listing_id = listing_id;
    }

    public @NotBlank(message = "Host id can not be empty") String getHost() {
        return host;
    }

    public void setHost(@NotBlank(message = "Host id can not be empty") String host) {
        this.host = host;
    }

    public @NotBlank @Max(value = 100, message = "Description max characters exceeded") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank @Max(value = 100, message = "Description max characters exceeded") String description) {
        this.description = description;
    }

    public @NotBlank(message = "No photo has been applied") String getPhoto() {
        return photo;
    }

    public void setPhoto(@NotBlank(message = "No photo has been applied") String photo) {
        this.photo = photo;
    }
}

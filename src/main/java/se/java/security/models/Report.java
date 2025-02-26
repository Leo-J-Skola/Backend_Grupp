package se.java.security.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reports")
public class Report {
    @Id
    private String reportId;

    @NotBlank(message = "User id can not be empty")
    private String UserId;

    @NotBlank(message = "Listing id can not be empty")
    private String ListingId;

    @NotBlank(message = "Host id can not be empty")
    private String host;

    @NotBlank
    @Max(value = 100, message = "Description max characters exceeded")
    private String description;

    @NotBlank(message = "No photo has been applied")
    private String photo;

    public Report(String reportId, String userId, String listingId, String host, String description, String photo) {
        this.reportId = reportId;
        this.UserId = userId;
        this.ListingId = listingId;
        this.host = host;
        this.description = description;
        this.photo = photo;
    }

    public Report() {
    }

    public String getId() {
        return reportId;
    }

    public void setId(String reportId) {
        this.reportId = reportId;
    }

    public @NotBlank(message = "User id can not be empty") String getUser_id() {
        return UserId;
    }

    public void setUser_id(@NotBlank(message = "User id can not be empty") String user_id) {
        this.UserId = user_id;
    }

    public @NotBlank(message = "Listing id can not be empty") String getListing_id() {
        return ListingId;
    }

    public void setListing_id(@NotBlank(message = "Listing id can not be empty") String listing_id) {
        this.ListingId = listing_id;
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

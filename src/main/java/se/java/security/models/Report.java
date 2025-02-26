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

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getListingId() {
        return ListingId;
    }

    public void setListingId(String listingId) {
        ListingId = listingId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}

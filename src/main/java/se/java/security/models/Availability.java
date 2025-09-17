package se.java.security.models;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;

import java.util.Date;



public class Availability {

    @Id
    private String id;

    @NotBlank(message = "startDate cannot be blank")
    private Date startDate;

    @NotBlank(message = "endDate cannot be blank")
    private Date endDate;

    @NotBlank(message = "listingId cannot be blank")
    private String listingId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getListingId() {
        return listingId;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
    }

    public Availability(String id, Date startDate, Date endDate, String listingId) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.listingId = listingId;
    }

    public Availability() {
    }
}

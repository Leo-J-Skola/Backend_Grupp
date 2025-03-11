package se.java.security.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "bookings")
@Valid
public class Booking {
    @Id
    private String id;

    @DBRef
    private User userId;

    @DBRef
    private Listing listingId;

    private Status status;

    @NotNull(message = "fee can not be null")
    private double fee;

    @NotNull(message = "Total amount can not be null")
    private double totalAmount;


    private Set<Availability> bookedDates = new HashSet<>();

    public Booking(String id, User userId, Listing listingId, Status status, double fee, double totalAmount, Set<Availability> bookedDates) {
        this.id = id;
        this.userId = userId;
        this.listingId = listingId;
        this.status = status;
        this.fee = fee;
        this.totalAmount = totalAmount;
        this.bookedDates = bookedDates;
    }

    public Booking() {
    }


    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Listing getListingId() {
        return listingId;
    }

    public void setListingId(Listing listingId) {
        this.listingId = listingId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Set<Availability> getBookedDates() {
        return bookedDates;
    }

    public void setBookedDates(Set<Availability> bookedDates) {
        this.bookedDates = bookedDates;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}

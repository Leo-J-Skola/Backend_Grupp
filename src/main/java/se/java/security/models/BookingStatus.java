package se.java.security.models;

public enum BookingStatus {
    AVAILABLE, // Listing can be booked during these dates
    PENDING, // When the request is pending, to prevent double bookings
    BOOKED, // When the object is already booked for those dates
    UNAVAILABLE // Other reasons such as admin removed
}
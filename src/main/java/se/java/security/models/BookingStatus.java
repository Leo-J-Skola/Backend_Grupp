package se.java.security.models;

public enum BookingStatus {
    AVAILABLE, // Listing can be booked during these dates
    PENDING, // When the request is pending, to prevent double bookings
    DECLINED, // Host has declined booking request
    ACCEPTED, // Host has accepted booking request
    UNAVAILABLE // Other reasons such as admin removed
}
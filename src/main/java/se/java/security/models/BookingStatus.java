package se.java.security.models;

public enum BookingStatus {
    AVAILABLE, // Listing can be booked during these dates
    PENDING, // When the request is pending, to prevent double bookings
    ACCEPTED, // Host has accepted the booking request
    DECLINED, // Host has declined the booking request
    UNAVAILABLE // Other reasons such as admin removed
}
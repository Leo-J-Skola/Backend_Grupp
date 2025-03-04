package se.java.security.services;

import org.springframework.stereotype.Service;
import se.java.security.exceptions.BookingReservedException;
import se.java.security.exceptions.BookingUnavailableException;
import se.java.security.models.Availability;
import se.java.security.models.Booking;

import se.java.security.models.Listing;
import se.java.security.models.Status;

import java.time.temporal.ChronoUnit;

@Service
public class BookingService {

    public void createBooking(Booking booking) {

        Status status = booking.getStatus();

        if (status == Status.UNAVAILABLE) {
            throw new BookingUnavailableException("Booking is unavailable");
        }
        if (status == Status.PENDING){
        throw new BookingReservedException("Booking is reserved");
    }
}


    public boolean confirmBooking(Booking booking) {

        boolean isConfirmed = booking.isAcceptedByHost();

        if (isConfirmed) {
            createBooking(booking);
        }else return false;
        return true;
    }

    public double calculatePrice(Booking booking, Listing listing, Availability availability) {

        long numberOfDays = ChronoUnit.DAYS.between(availability.getStartDate(), availability.getEndDate());

        double totalPrice = listing.getPricePerNight() * numberOfDays + booking.getFee();


        System.out.println("Total price per day: " + totalPrice);

        System.out.println(numberOfDays);

        System.out.println(listing.getPricePerNight());

        booking.setTotalAmount(totalPrice);

        return totalPrice;

    }
}

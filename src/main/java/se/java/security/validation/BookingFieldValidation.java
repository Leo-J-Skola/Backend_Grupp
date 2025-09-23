package se.java.security.validation;

import org.springframework.stereotype.Service;
import se.java.security.authentication.AuthenticationService;
import se.java.security.dto.BookingRequest;
import se.java.security.models.BookingStatus;

@Service
public class BookingFieldValidation {
    private final AuthenticationService authenticationService;

    public BookingFieldValidation(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public void validateBookingRequestData(BookingRequest bookingRequest) {
        // Set status to pending if status is null
        if(bookingRequest.getStatus() == null ){
            bookingRequest.setStatus(BookingStatus.PENDING);
        }
        // Throws an error if userId is null
        if(authenticationService.getCurrentUser().getId() == null){
            throw new IllegalArgumentException("userId is null");
        }
        // Throws an error if listingId is null
        if(bookingRequest.getListingId() == null){
            throw new IllegalArgumentException("listingId is null");
        }
        // Throws an error if fee is less than or equal to zero
        if(bookingRequest.getFee() <= 0 ){
            throw new IllegalArgumentException("Fee can't be less than or equal to 0");
        }
        // Throws an error if total amount is less than or equal to zero
        if(bookingRequest.getTotalAmount()<= 0 ){
            throw new IllegalArgumentException("Total amount can't be less than or equal to 0");
        }
        // Throws an error if startDate or endDate is a null value
        if(bookingRequest.getStartDate() == null || bookingRequest.getEndDate() == null){
            throw new IllegalArgumentException("startDate or endDate can't be null");
        }
        // Validation check to see if endDate is before the startDate
        if(bookingRequest.getStartDate().after(bookingRequest.getEndDate())){
            throw new IllegalArgumentException("endDate can't be before startDate");
        }

    }
}

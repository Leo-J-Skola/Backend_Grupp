package se.java.security.services;

import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class PriceCalculationService {
        private static final double feePercentage = 0.05; // 5% service fee that goes to Homi

        // This calculates the total amount including the fee by multiplying the number of nights with the price per night
        public double calculateTotalAmount(LocalDate startDate, LocalDate endDate, double pricePerNight) {
            if (startDate == null || endDate == null || pricePerNight < 0) {
                throw new IllegalArgumentException("Invalid input. You must provide valid start date, end date and price per night.");
            }

            long amountOfNights = endDate.toEpochDay() - startDate.toEpochDay();
            if (amountOfNights <= 0) {
                throw new IllegalArgumentException("End date must be after start date");
            }
            double totalAmount = amountOfNights * pricePerNight;
            double fee = totalAmount * feePercentage;
            return totalAmount + fee;
        }

        // And this method calculates the fee percentage that Homi will get
        public double calculateFee(double totalAmount) {
            if (totalAmount < 0) {
                throw new IllegalArgumentException("Total amount cannot be less than zero");
            }
            return totalAmount * feePercentage;
        }
}

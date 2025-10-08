## Project overview
We are doing a refactor of our booking feature to make the code easier to maintain and to extend in the future. Our main goals are to use authorization, validation for booking dates, object creation and accepting a pending booking by using design patterns.

## What we've done
- Implemented strategy pattern to validate booking dates.
- Factory pattern to create booking objects in a consistent way.
- Added booking authorization for improved code structure.

## Design patterns used

### Strategy pattern for Booking validation
- Problem before refactor: The old code had no way to check dates.
- Solutions: We created a method in bookingService that compares the current bookingRequest with existing booking objects linked to that specific listing. We also created bookingValidationStrategy interface with a strategy class called overlapBoookingValidationStrategy
- Benefit: It now validates the booking request dates so there won't be any overlaps. Its also easier to add new types of validation later.

### Factory pattern for Booking creation
- Problem before refactor: 
- Solution: 
- Benefit: 

## Diagrams

### Class diagram
<img width="1101" height="733" alt="bild" src="https://github.com/user-attachments/assets/964afc46-0432-4bb6-ba75-0779e725c633" />

### Sequence diagram
<img width="881" height="703" alt="bild" src="https://github.com/user-attachments/assets/36ec0556-e2f2-4de4-99a4-834165f8500d" />

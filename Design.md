## Introduction

### Description
We are doing a refactor of our booking feature to make the code easier to maintain and to extend in the future. Our main goals are to use authorization, validation for booking dates, object creation and accepting a pending booking by using design patterns.

### Scope
What is included in our refactor:
- Authentication and authorization for booking actions
- Validation of booking dates using strategy pattern
- Creation of booking objects using factory pattern
What is not included in our refactor:
- Anything frontend related
- Functionality outside of our booking feature such as listing, favorites, ratings

## Initial situation
Before the refactor, the booking logic was placed directly inside the service and controller classes. This made it really hard to read, maintain and extend our code.

### Problems we found
- We had no way to validate overlapping booking dates
- Booking creation: 
- Authorization and authorization:
- There we're no functionality to accept a pending booking request
- The code was hard to read or debug, since many classes were doing way too many things

## Design choises

### Principles used

### Design patterns used
- Strategy pattern:
- Factory pattern:

### UML

### Class diagram
<img width="1101" height="733" alt="bild" src="https://github.com/user-attachments/assets/964afc46-0432-4bb6-ba75-0779e725c633" />

### Sequence diagram
<img width="881" height="703" alt="bild" src="https://github.com/user-attachments/assets/36ec0556-e2f2-4de4-99a4-834165f8500d" />

## Solution
After refactoring, the booking logic is now easier to maintain and to extend further in the future

### New structure
- BookingService: uses different parts in the process, auth check, validation, creation
- BookingValidationStrategy: interface for validation strategies
- OverlapBookingValidationStrategy: checks for overlapping bookings
- BookingFactory: creates booking objcets

### How it works
- The user sends a booking request
- bookingService checks authorization
- The service calls the chosen validation strategy to confirm valid dates
- The BookingFactory creates the booking object
- The booking request is accepted by the host
- The booking object is saved

### Benefits
- Cleaner code and clear separation of responsobilites
- Easier to add new validation types
- Booking creation is consistent 

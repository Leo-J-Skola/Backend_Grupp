# **Refactor Booking** 

## 1\. Introduction

1.1 Background

The Booking Refactor system enables users to authenticate, and make booking requests. The system validates user input, ensures secure authentication/authorization, and manages booking statuses between users and hosts.

## 1.2 Scope

The Booking Refactor system supports:  

* Price calculation based on total days selected (StartDate, EndDate).  
* Prevent double bookings.  
* Sending and processing booking requests.

## 1.2.1 Purpose

We refactored the *BookingService* class by splitting up its methods. Originally, it handled multiple responsibilities at once, such as creating bookings, calculating prices, and accepting bookings.

## 1.3 Extent

This document includes both functional and non functional requirements.

## 2\. Stakeholders

| Group A | Development team |
| :---- | :---- |
| Group B | Users |
| Project team | Product owner |

## 3\. Requirement specification

### 3.1 Functional Requirements

### 3.1.1 \- Authentication

* FR 001:  A user must be able to login with their username and password  
* FR 002: The system must authenticate the user with login details  
* FR 003: The system should display error messages (example: "Invalid credentials",    "Authentication failed").

---

### 3.1.2 \- Bookings

* FR 004: The system must authorize users before a booking request can be made  
* FR 005: The system must validate all fields before a booking request can be made  
* FR 006: A user must be authenticated before a booking request can be made  
* FR 007: The system should display a success message if a booking request is successfully created

---

### 3.1.3 \- Prices

* FR 008:  
* FR 009:

---

### 3.1.4 \- Accept or decline booking request

* FR 010: Bookings can only be accepted or declined if it has not already been accepted/declined  
* FR 011: The system will verify if the booking object exists by searching for the object id  
* FR 012: A User must be authorized and authenticated(logged in as listing owner) to be able to accept or decline a pending request  
* FR 013: The system only allows accept or decline booking request if it has status pending  
* FR 014: The system should notify the guest user once booking has been accepted or declined

---

### 3.2 Non Functional Requirements

### 	3.2.1 Security

* NFR 001: All passwords must be hashed before they are saved in the database.  
* NFR 002: Secure validation to prevent unauthorized access

### 	3.2.2 Utility

* NFR 003: The UI should be simple and easy to navigate around on.

### 	3.2.3 Usability

* NFR 004: Clear error messages must be displayed to guide the user on the website.


  


  


  


  


  


  


  


  





### 4\. Priorities and dependencies

| Claim ID | Priority | Dependency |
| ----- | ----- | ----- |
| FR-001 | Must have | \- |
| FR-002 | Must have | FR-001 |
| FR-003 | Should have | FR-002 |
| FR-004 | Must have | FR-001 |
| FR-005 | Must have | \- |
| FR-006 | Must have | FR-005 |
| FR-007 | Should have | \- |
| FR-008 | \- | \- |
| FR-009 | \- | \- |
| FR-010 | Must have | FR-011 |
| FR-011 | Must have | FR-012 |
| FR-012 | Must have | FR-010 |
| FR-013 | Must have | FR-012 |
| FR-014 | Should have | FR-013 |
|  |  |  |
| NFR-001 |  | FR-002 |
| NFR-002 |  | \- |
| NFR-003 |  | \- |
| NFR-004 |  | \- |
|  |  |  |


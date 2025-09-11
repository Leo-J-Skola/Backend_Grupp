# 🏡 Project Overview: Airbnb Clone

This project is our custom-built version of **Airbnb**, featuring core functionalities such as **user management**, **property listings**, and **booking services**. All data is stored using **MongoDB**.

Our current focus is a **refactor of the booking system** to enhance performance and scalability.

---

## 🚀 How to Run the Project

1. **Clone or Fork the Repository**
   - GitHub URL: [https://github.com/Leo-J-Skola/Backend_Grupp.git](https://github.com/Leo-J-Skola/Backend_Grupp.git)
   - Or via CLI:
     ```bash
     gh repo clone Leo-J-Skola/Backend_Grupp
     ```

2. **Open the Project**
   - Use your favorite IDE (e.g., VS Code, IntelliJ, etc.)

3. **Install Postman**
   - Required to test the API endpoints effectively.

---

## 🔧 API Overview

### 📁 General CRUD Operations

Supports the following HTTP methods:
- `POST` / `PUT` / `GET` / `DELETE` / `PATCH`

---

## 👤 User Endpoints

| Action         | Endpoint                                  |
|----------------|--------------------------------------------|
| Register       | `http://localhost:8080/auth/register`     |
| Login          | `http://localhost:8080/auth/login`        |
| Logout         | `http://localhost:8080/auth/logout`       |

---

## 🏘️ Listing Endpoints

| Action         | Endpoint                                          |
|----------------|---------------------------------------------------|
| Create         | `http://localhost:8080/listing/create`           |
| Update         | `http://localhost:8080/listing/:id`              |
| Delete         | `http://localhost:8080/listing/delete/:id`       |

---

## 📅 Booking Endpoints

| Action         | Endpoint                                          |
|----------------|---------------------------------------------------|
| Create Request | `http://localhost:8080/booking/request`          |
| Confirm/Delete | `http://localhost:8080/booking/:id`              |

> ⚠️ *Note: Confirm and Delete share the same endpoint; the method used (e.g., PUT, DELETE) determines the action.*

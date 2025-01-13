
# Triplodge

Triplodge is a comprehensive platform for booking and managing vacation rentals. It allows users to book listings, leave reviews, send messages, and more. Hosts can create and manage listings, set availability, and handle bookings. The application uses JWT (JSON Web Tokens) for stateless authentication and Spring Security for securing the endpoints.

---

## Features

- **User Authentication & Authorization**:
  - Registration and Login using JWT for stateless authentication.
  - Role-based access control (Admin, Host, Guest).

- **Listings**:
  - Hosts can create and manage listings.
  - Search and filter listings by location, price, availability, and more.

- **Bookings**:
  - Guests can book listings based on availability.
  - View and update booking details.
  
- **Reviews**:
  - Guests can leave reviews for listings they have stayed in.
  - View reviews of listings.

- **Messaging**:
  - Hosts and guests can message each other to communicate about bookings or other matters.

- **Admin Dashboard**:
  - Admins can view all users, listings, bookings, and generate reports.

---

## Technologies Used

- **Spring Boot**: Backend framework for building the REST API.
- **Spring Security**: Security and authentication for API endpoints.
- **JWT**: Stateless authentication using JSON Web Tokens.
- **JPA/Hibernate**: Object-relational mapping for database interactions.
- **MySQL**: Relational database management system to store data.

---

## API Endpoints

### User Authentication

- **POST /api/auth/register**: Registers a new user.
- **POST /api/auth/login**: Logs in a user and returns a JWT token.

### Listings

- **GET /api/listings**: Retrieves all listings.
- **GET /api/listings/{id}**: Retrieves a specific listing by its ID.
- **POST /api/listings**: Creates a new listing.
- **PUT /api/listings/{id}**: Updates an existing listing.
- **DELETE /api/listings/{id}**: Deletes a listing.

### Bookings

- **POST /api/bookings**: Books a listing for specified dates.
- **GET /api/bookings/{id}**: Retrieves details of a specific booking.
- **PUT /api/bookings/{id}**: Updates the status of a booking.
- **DELETE /api/bookings/{id}**: Cancels a booking.

### Reviews

- **POST /api/reviews**: Submits a review for a completed booking.
- **GET /api/reviews/listing/{listingId}**: Retrieves all reviews for a specific listing.
- **DELETE /api/reviews/{id}**: Deletes a review.

### Messaging

- **POST /api/messages**: Sends a message to another user.
- **GET /api/messages/conversation/{userId}**: Retrieves messages for the user.

### Admin

- **GET /api/admin/users**: Fetches a list of all users.
- **GET /api/admin/reports**: Generates analytics and reports for the admin dashboard.


## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

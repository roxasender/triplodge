package com.nexus.triplodge.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexus.triplodge.dto.BookingDto;
import com.nexus.triplodge.service.IBookingService;

import lombok.RequiredArgsConstructor;

import java.net.http.HttpClient;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {
    private final IBookingService bookingService;

    // POST /api/bookings: Books a listing for specified dates
    @PostMapping("/")
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingDto bookingDto) {
        BookingDto createdBooking = bookingService.createBooking(bookingDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBooking);
    }

    // GET /api/bookings/{id}: Retrieves details of a specific booking.
    @GetMapping("/")
    public ResponseEntity<BookingDto> getBookingById(@RequestParam Long id) {
        return bookingService.getBookingById(id)
                .map(booking -> ResponseEntity.status(HttpStatus.OK).body(booking))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
    
    // PUT /api/bookings/{id}/status: Updates booking status (accept/decline).
    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateBookingStatus(@PathVariable Long id, @RequestBody BookingDto request) {
        try {
            bookingService.updateBookingStatus(id, request);
            return ResponseEntity.status(HttpStatus.OK).body("Updated booking status successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Failed to Update booking status");
        }
    }

    // GET /api/bookings/user/{userId}: Retrieves all bookings for a specific user (guest or host).
    @GetMapping("/user/")
    public ResponseEntity<List<BookingDto>> getAllBookingsByUserId(@RequestParam Long userId) {
        List<BookingDto> bookings = bookingService.getAllBookingsByUserId(userId);
        return ResponseEntity.ok(bookings);
    }
    

    // DELETE /api/bookings/{id}: Cancels a booking (guest only).
    @DeleteMapping("/")
    public ResponseEntity<String> deleteBookingById(@RequestParam Long id) {
        try {
            bookingService.deleteBookingById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
        }
    }
    
}

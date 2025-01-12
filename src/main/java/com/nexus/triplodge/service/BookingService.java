package com.nexus.triplodge.service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nexus.triplodge.dto.BookingDto;
import com.nexus.triplodge.exception.NotExistsException;
import com.nexus.triplodge.model.Booking;
import com.nexus.triplodge.model.BookingStatus;
import com.nexus.triplodge.model.Listing;
import com.nexus.triplodge.repository.AvailabilityRepository;
import com.nexus.triplodge.repository.BookingRepository;
import com.nexus.triplodge.repository.ListingRepository;
import com.nexus.triplodge.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService implements IBookingService{
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ListingRepository listingRepository;
    private final AvailabilityRepository availabilityRepository;

    @Override
    public BookingDto createBooking(BookingDto bookingDto) {
        // Validate availability
        boolean isAvailable = availabilityRepository.isAvailableForDates(
            bookingDto.getListingId(),
            bookingDto.getCheckInDate(),
            bookingDto.getCheckOutDate()
        );
        if (!isAvailable) {
            throw new IllegalStateException("The listing is not available for the specified dates.");
        }

        // Create a new booking
        Booking booking = new Booking();
        booking.setGuest(userRepository.findById(bookingDto.getGuestId())
            .orElseThrow(() -> new EntityNotFoundException("Guest not found")));
        booking.setListing(listingRepository.findById(bookingDto.getListingId())
            .orElseThrow(() -> new EntityNotFoundException("Listing not found")));
        booking.setCheckInDate(bookingDto.getCheckInDate());
        booking.setCheckOutDate(bookingDto.getCheckOutDate());
        booking.setStatus(BookingStatus.PENDING); // Default status
        booking.setTotalPrice(calculateTotalPrice(bookingDto));

        // Save the booking
        booking = bookingRepository.save(booking);

        // Map and return the result as BookingDto
        return convertToBookingDto(booking);
    }

    private BigDecimal calculateTotalPrice(BookingDto bookingDto) {
        Listing listing = listingRepository.findById(bookingDto.getListingId())
            .orElseThrow(() -> new EntityNotFoundException("Listing not found"));
        long days = ChronoUnit.DAYS.between(bookingDto.getCheckInDate(), bookingDto.getCheckOutDate());
        return listing.getPricePerNight().multiply(BigDecimal.valueOf(days));
    }

    @Override
    public Optional<BookingDto> getBookingById(Long id) {
        return bookingRepository.findById(id)
            .map(this::convertToBookingDto);
    }

    private BookingDto convertToBookingDto(Booking booking) {
        BookingDto dto = new BookingDto();
        
        // Set primitive and directly mappable fields
        dto.setId(booking.getId());
        dto.setCheckInDate(booking.getCheckInDate());
        dto.setCheckOutDate(booking.getCheckOutDate());
        dto.setStatus(booking.getStatus().toString());
        dto.setTotalPrice(booking.getTotalPrice());
    
        // Handle nested entities
        if (booking.getGuest() != null) {
            dto.setGuestId(booking.getGuest().getId());
        }
    
        if (booking.getListing() != null) {
            dto.setListingId(booking.getListing().getId());
        }
    
        if (booking.getReview() != null) {
            dto.setReviewId(booking.getReview().getId());
        }
    
        if (booking.getPayment() != null) {
            dto.setPaymentId(booking.getPayment().getId());
        }
    
        return dto;
    }


    @Override
    public void updateBookingStatus(Long id, BookingDto dto) {
        Booking booking = bookingRepository.findById(id)
            .orElseThrow(() -> new NotExistsException("Booking not found with id: " + id));
        
        if (dto.getStatus() != null) {
            booking.setStatus(BookingStatus.valueOf(dto.getStatus().toUpperCase()));
        }
        bookingRepository.save(booking);
        
    }

    @Override
    public List<BookingDto> getAllBookingsByUserId(Long userId) {
        List<Booking> bookings = bookingRepository.findAllByGuestId(userId);

        return bookings.stream()
            .map(this::convertToBookingDto)
            .collect(Collectors.toList());
    }

    @Override
    public void deleteBookingById(Long id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Booking with ID " + id + " not found.");
        }
    }
}

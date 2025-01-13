package com.nexus.triplodge.service;

import java.util.List;
import java.util.Optional;

import com.nexus.triplodge.dto.BookingDto;

public interface IBookingService {
    BookingDto createBooking(BookingDto request);
    Optional<BookingDto> getBookingById(Long id);
    void updateBookingStatus(Long id, BookingDto request);
    List<BookingDto> getAllBookingsByUserId(Long userId);
    void deleteBookingById(Long id);
}

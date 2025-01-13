package com.nexus.triplodge.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.nexus.triplodge.dto.ReportDto;
import com.nexus.triplodge.repository.BookingRepository;
import com.nexus.triplodge.repository.ListingRepository;
import com.nexus.triplodge.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService implements IReportService {
    private final UserRepository userRepository;
    private final ListingRepository listingRepository;
    private final BookingRepository bookingRepository;

    @Override
    public ReportDto generateReports() {
        long totalUsers = userRepository.count();
        long totalListings = listingRepository.count();
        long totalBookings = bookingRepository.count();

        BigDecimal totalRevenue = bookingRepository.calculateTotalRevenue();

        return new ReportDto(totalUsers, totalListings, totalBookings, totalRevenue);
    }
}

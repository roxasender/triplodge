package com.nexus.triplodge.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ReportDto {
    private long totalUsers;
    private long totalListings;
    private long totalBookings;
    private BigDecimal totalRevenue;

    public ReportDto (long totalUsers, long totalListings, long totalBookings, BigDecimal totalRevenue) {
        this.totalUsers = totalUsers;
        this.totalListings = totalListings;
        this.totalBookings = totalBookings;
        this.totalRevenue = totalRevenue;
    }
}

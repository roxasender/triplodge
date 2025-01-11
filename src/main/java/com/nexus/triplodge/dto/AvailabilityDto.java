package com.nexus.triplodge.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AvailabilityDto {

    private Long id;

    private Long listingId;

    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isAvailability;  
}
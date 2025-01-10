package com.nexus.triplodge.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ListingFilterDto {
    private String location;
    private BigDecimal pricePerNight;
    private int maxGuest;
}

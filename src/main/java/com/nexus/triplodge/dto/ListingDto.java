package com.nexus.triplodge.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.nexus.triplodge.model.Availability;

import lombok.Data;

@Data
public class ListingDto {
    private Long id;

    private Long hostId;
    private Date startDate;
    private Date endDate;
    private String title;
    private String description;
    private String location;
    private BigDecimal pricePerNight;
    private int maxGuest;
    private Set<Availability> availabilities;

    public ListingDto(Long hostId, String title, String description, String location, BigDecimal pricePerNight, int maxGuest) {
        this.hostId = hostId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.pricePerNight = pricePerNight;
        this.maxGuest = maxGuest;
        // this.startDate = startDate;
        // this.endDate = endDate;
    }
}

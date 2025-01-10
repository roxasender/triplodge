package com.nexus.triplodge.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexus.triplodge.dto.ListingFilterDto;
import com.nexus.triplodge.model.Listing;
import com.nexus.triplodge.service.IListingService;

import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/listing")
@RequiredArgsConstructor
public class ListingController {
    private final IListingService listingService;

    // Search for listings based on filter
    @GetMapping("/search")
    public ResponseEntity<List<Listing>> searchListing(
        @RequestParam(required = false) Long hostId,
        @RequestBody ListingFilterDto listingFilterDto, 
        @RequestParam(required = false) Date startDate, 
        @RequestParam(required = false) Date endDate
        ) {
        List<Listing> listings = listingService.searchListing(hostId, listingFilterDto, startDate, endDate);
        return listings.isEmpty() 
            ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(null) 
            : ResponseEntity.status(HttpStatus.OK).body(listings);
    }
    
}

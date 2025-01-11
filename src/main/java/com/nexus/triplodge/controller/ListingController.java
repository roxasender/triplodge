package com.nexus.triplodge.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexus.triplodge.dto.ListingDto;
import com.nexus.triplodge.dto.ListingFilterDto;
import com.nexus.triplodge.service.IListingService;

import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/listing")
@RequiredArgsConstructor
public class ListingController {
    private final IListingService listingService;

    // Search for listings based on filter
    @GetMapping("/search")
    public ResponseEntity<List<ListingDto>> searchListing(
        @RequestParam(required = false) Long hostId,
        @RequestBody ListingFilterDto listingFilterDto, 
        @RequestParam(required = false) String startDate, 
        @RequestParam(required = false) String endDate
        ) {
        List<ListingDto> listings = listingService.searchListing(hostId, listingFilterDto, Date.valueOf(startDate), Date.valueOf(endDate));
        return ResponseEntity.status(HttpStatus.OK).body(listings);
    }

    // GET /api/listing/{id} Retrieves detailed information about a specific listing.
    @GetMapping("/{id}")
    public ResponseEntity<ListingDto> getListingById(@PathVariable Long id) {
        ListingDto listing = listingService.getListingById(id);
        return listing != null ? ResponseEntity.status(HttpStatus.OK).body(listing) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        // return ResponseEntity.status(HttpStatus.OK).body(listing);
    }
    
    // POST /api/listing: Creates a new listing.
    @PostMapping("/")
    public ResponseEntity<String> createListing(@RequestBody ListingDto requestListing) {
        try {
            listingService.createListing(requestListing);
            return ResponseEntity.status(HttpStatus.OK).body("Created successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Created Listing fail");
        }
    }
    

    // PUT /api/listings/{id}: Updates an existing listing.
    @PutMapping("/{id}")
    public ResponseEntity<String> updateListing(@PathVariable Long id, @RequestBody ListingDto requestListing) {
        if (listingService.getListingById(id) == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        try {
            listingService.updateListing(requestListing);
            return ResponseEntity.status(HttpStatus.OK).body("Updated listing successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Failed to update listing");
        }
    }

    // DELETE /api/listings/{id}: Deletes a listing.
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteListing(@PathVariable Long id) {
        try {
            listingService.deleteListing(id);
            return ResponseEntity.status(HttpStatus.OK).body("Deleted listing successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Failed to delete listing");
        }
    }
}

package com.nexus.triplodge;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nexus.triplodge.dto.ListingDto;
import com.nexus.triplodge.model.Listing;
import com.nexus.triplodge.repository.ListingRepository;

@SpringBootTest
public class ListingRepositoryTest {
    @Autowired
    private ListingRepository listingRepository;

    @Test
    public void testFindByFilter() {
        // Given: Some search parameters
        Long hostId = 1L;
        String location = "New York";
        Integer maxGuest = 4;
        BigDecimal pricePerNight = new BigDecimal("150");
        Date startDate = Date.valueOf("2025-01-01");
        Date endDate = Date.valueOf("2025-01-10");

        // Long hostId = 1L;
        // String location = "New York";
        // Integer maxGuest = 4;
        // BigDecimal pricePerNight = new BigDecimal("150");
        // String startDate = "2025-01-01";
        // String endDate = "2025-01-10";

        // Long hostId = null;
        // String location = "New York";
        // Integer maxGuest = 4;
        // BigDecimal pricePerNight = null;
        // String startDate = null;
        // Date endDate = null;

        // When: Call the repository method directly
        List<Listing> listings = listingRepository.findByFilter(
                hostId,
                location,
                maxGuest,
                pricePerNight,
                startDate,
                endDate
        );
        List<ListingDto> listingDtos = listings.stream().map(list -> new ListingDto(list.getHost().getId(), list.getTitle(), list.getDescription(), list.getLocation(), list.getPricePerNight(), list.getMaxGuest())).collect(Collectors.toList());

        System.out.println(listingDtos);
        // Then: Assert the results
        assertNotNull(listingDtos);
        // assertFalse(listings.isEmpty());
        // You can add more assertions depending on expected results
    }
}

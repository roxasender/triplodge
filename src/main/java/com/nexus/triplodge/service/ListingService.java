package com.nexus.triplodge.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nexus.triplodge.dto.ListingFilterDto;
import com.nexus.triplodge.model.Listing;
import com.nexus.triplodge.repository.ListingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListingService implements IListingService {
    private final ListingRepository listingRepository;

    @Override
    public List<Listing> searchListing(Long hostId, ListingFilterDto listingFilterDto, Date startDate, Date endDate) {
        System.out.println(hostId);
        System.out.println(listingFilterDto.getLocation());
        System.out.println(listingFilterDto.getMaxGuest());
        System.out.println(listingFilterDto.getPricePerNight());
        System.out.println(startDate);
        System.out.println(endDate);
        return listingRepository.findByFilter(
            hostId,
            listingFilterDto.getLocation(),
            listingFilterDto.getMaxGuest(),
            listingFilterDto.getPricePerNight(),
            startDate,
            endDate
        );
    }
}

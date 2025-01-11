package com.nexus.triplodge.service;

import java.util.Date;
import java.util.List;

import com.nexus.triplodge.dto.ListingDto;
import com.nexus.triplodge.dto.ListingFilterDto;

public interface IListingService {
    List<ListingDto> searchListing(Long hostId, ListingFilterDto listingFilterDto, Date startDate, Date endDate);
    ListingDto getListingById(Long id);
    void createListing(ListingDto requestListing);
    void updateListing(ListingDto requestListing);
    void deleteListing(Long id);
}

package com.nexus.triplodge.service;

import java.util.Date;
import java.util.List;

import com.nexus.triplodge.dto.ListingFilterDto;
import com.nexus.triplodge.model.Listing;

public interface IListingService {
    List<Listing> searchListing(Long hostId, ListingFilterDto listingFilterDto, Date startDate, Date endDate);
}

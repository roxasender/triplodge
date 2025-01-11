package com.nexus.triplodge.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.nexus.triplodge.dto.ListingDto;
import com.nexus.triplodge.dto.ListingFilterDto;
import com.nexus.triplodge.exception.NotExistsException;
import com.nexus.triplodge.model.Availability;
import com.nexus.triplodge.model.Listing;
import com.nexus.triplodge.repository.AvailabilityRepository;
import com.nexus.triplodge.repository.ListingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ListingService implements IListingService {
    private final ListingRepository listingRepository;
    private final AvailabilityRepository availabilityRepository;

    @Override
    public List<ListingDto> searchListing(Long hostId, ListingFilterDto listingFilterDto, Date startDate, Date endDate) {
        System.out.println(hostId);
        System.out.println(listingFilterDto);
        System.out.println(startDate);
        System.out.println(endDate);

        List<Listing> listings = listingRepository.findByFilter(
            hostId,
            listingFilterDto.getLocation(),
            listingFilterDto.getMaxGuest(),
            listingFilterDto.getPricePerNight(),
            startDate,
            endDate
        );
        System.out.println(listings);
        return listings.stream()
                       .map(listing -> new ListingDto(
                        listing.getId(),
                        listing.getTitle(),
                        listing.getDescription(),
                        listing.getLocation(),
                        listing.getPricePerNight(),
                        listing.getMaxGuest()
                        ))
                       .collect(Collectors.toList());
    }

    @Override
    public ListingDto getListingById(Long id) {
        Optional<Listing> listing = listingRepository.findById(id);
        if (listing.isPresent()) return new ListingDto(
            listing.get().getId(), 
            listing.get().getTitle(), 
            listing.get().getDescription(), 
            listing.get().getLocation(), 
            listing.get().getPricePerNight(), 
            listing.get().getMaxGuest());
        return null;
    }

    @Override
    public void createListing(ListingDto requestListing) {
        Listing listing = new Listing();
        listing.setTitle(requestListing.getTitle());
        listing.setDescription(requestListing.getDescription());
        listing.setLocation(requestListing.getLocation());
        listing.setPricePerNight(requestListing.getPricePerNight());
        listing.setMaxGuest(requestListing.getMaxGuest());
        
        Set<Availability> availabilities = new HashSet<>();
        for (Availability availability : requestListing.getAvailabilities()) {
            availability.setListing(listing);
        }
        listing.setAvailabilities(availabilities); 
        listingRepository.save(listing);
        availabilityRepository.saveAll(availabilities);
    }

    @Override
    public void updateListing(ListingDto requestListing) {
        if (!listingRepository.existsById(requestListing.getId())) throw new NotExistsException("Request List: " + requestListing.getId() + " not exists");
        try {
            Listing listing = listingRepository.findById(requestListing.getId()).get();
            listing.setTitle(requestListing.getTitle());
            listing.setDescription(requestListing.getDescription());
            listing.setLocation(requestListing.getLocation());
            listing.setMaxGuest(requestListing.getMaxGuest());
            listing.setPricePerNight(requestListing.getPricePerNight());

            updateAvailabilities(listing, requestListing.getAvailabilities());

            listingRepository.save(listing);
        } catch (Exception e) {
            throw new IllegalArgumentException("updated listing fail");
        }
    }

    public void updateAvailabilities(Listing listing, Set<Availability> newAvailabilities) {
        listing.getAvailabilities().clear();

        for (Availability newAvailability : newAvailabilities) {
            newAvailability.setListing(listing);
            listing.getAvailabilities().add(newAvailability);
        }
    }

    @Override
    public void deleteListing(Long id) {
        Optional<Listing> listing = listingRepository.findById(id);
        if (listing.isPresent()) {
            listingRepository.deleteById(id);
        } else {
            // Throw an exception or handle case where listing doesn't exist
            throw new NotExistsException("Listing not found with id: " + id);
        }
    }
}

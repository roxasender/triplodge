package com.nexus.triplodge.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nexus.triplodge.model.Listing;

@Repository
public interface ListingRepository extends JpaRepository<Listing, Long> {
    // @Query("SELECT l FROM Listing l JOIN l.availabilities a WHERE " +
    //     "(:hostId IS NULL OR l.host_id = :hostId) AND " +
    //     "(:location IS NULL OR l.location LIKE %:location%) AND " +
    //     "(:maxGuest IS NULL OR l.max_guest >= :maxGuest) AND " +
    //     "(:pricePerNight IS NULL OR l.price_per_night <= :pricePerNight) AND " +
    //     "(:startDate IS NULL OR a.startDate <= :startDate) AND " +
    // //     "(:endDate IS NULL OR a.endDate >= :endDate)")
    @Query(value = "SELECT l.* FROM listing l " +
        "JOIN availability a ON l.id = a.listing_id " +
        "WHERE (:hostId IS NULL OR l.host_id = :hostId)" +
        "AND (:location IS NULL OR l.location LIKE CONCAT('%', :location, '%')) " +
        "AND (:maxGuest IS NULL OR l.max_guest >= :maxGuest) " +
        "AND (:pricePerNight IS NULL OR l.price_per_night <= :pricePerNight) " +
        "AND (:startDate IS NULL OR a.start_date <= :startDate) " +
        "AND (:endDate IS NULL OR a.end_date >= :endDate)", nativeQuery = true)
    // @Query(value = "SELECT l.* FROM listing l " +
    //     "JOIN availability a ON l.id = a.listing_id " +
    //     "WHERE (:hostId IS NULL OR l.host_id = :hostId)", nativeQuery = true)
    List<Listing> findByFilter(
        @Param("hostId") Long hostId,
        @Param("location") String location,
        @Param("maxGuest") int maxGuest,
        @Param("pricePerNight") BigDecimal pricePerNight,
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate
        );
}

package com.nexus.triplodge.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nexus.triplodge.model.Availability;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    @Query(value = "SELECT CASE WHEN COUNT(a) > 0 THEN TRUE ELSE FALSE END " +
                   "FROM availability a " +
                   "WHERE a.listing_id = :listingId " +
                   "AND a.is_available = TRUE " +
                   "AND a.date BETWEEN :startDate AND :endDate", nativeQuery = true)
    boolean isAvailableForDates(@Param("listingId") Long listingId,
                                @Param("startDate") LocalDate startDate,
                                @Param("endDate") LocalDate endDate);
}

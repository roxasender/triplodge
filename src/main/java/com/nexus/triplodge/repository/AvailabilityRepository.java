package com.nexus.triplodge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexus.triplodge.model.Availability;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    
}

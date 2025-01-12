package com.nexus.triplodge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexus.triplodge.model.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByGuestId(Long guestId);
}

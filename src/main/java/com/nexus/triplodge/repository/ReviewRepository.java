package com.nexus.triplodge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nexus.triplodge.model.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query(value = "SELECT * " +
                   "FROM review r " +
                   "JOIN booking b ON r.booking_id = b.id " +
                   "WHERE r.guest_id = :userId OR b.guest_id = :userId", nativeQuery = true)
    List<Review> findReviewsByUserId(@Param("userId") Long userId);
}

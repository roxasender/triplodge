package com.nexus.triplodge.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    // GET /api/reviews/user/{userId}: Fetches reviews written by or about a specific user.
    // POST /api/reviews: Submits a review for a completed booking.
    // GET /api/reviews/listing/{listingId}: Retrieves all reviews for a specific listing.
    // DELETE /api/reviews/{id}: Deletes a review (admin only).

}

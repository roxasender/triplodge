package com.nexus.triplodge.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexus.triplodge.dto.ReviewDto;
import com.nexus.triplodge.service.IReviewService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final IReviewService reviewService;

    // GET /api/reviews/user/{userId}: Fetches reviews written by or about a specific user.
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReviewDto>> getReviewsByUserId(@PathVariable Long userId) {
        List<ReviewDto> reviews = reviewService.getReviewsByUserId(userId);
        if (reviews.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(reviews);  
        }
        return ResponseEntity.status(HttpStatus.OK).body(reviews);
    }
    
    // POST /api/reviews: Submits a review for a completed booking.
    @PostMapping("/")
    public ResponseEntity<ReviewDto> submitReview(@RequestBody ReviewDto reviewDto) {
        try {
            ReviewDto savedReview = reviewService.submitReview(reviewDto);
            return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    // GET /api/reviews/listing/{listingId}: Retrieves all reviews for a specific listing.
    // @GetMapping("/listing/{listingId}")
    // public ResponseEntity<List<ReviewDto>> getReviewsForListing(@PathVariable Long listingId) {
    //     try {
    //         List<ReviewDto> reviews = reviewService.getReviewsForListing(listingId);
    //         if (reviews.isEmpty()) {
    //             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //         }
    //         return new ResponseEntity<>(reviews, HttpStatus.OK);
    //     } catch (RuntimeException e) {
    //         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    //     }
    // }
    

    // DELETE /api/reviews/{id}: Deletes a review (admin only).
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id) {
        try {
            reviewService.deleteReview(id);
            return new ResponseEntity<>("Review deleted successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
        }
    }

}

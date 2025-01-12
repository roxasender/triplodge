package com.nexus.triplodge.service;

import java.util.List;

import com.nexus.triplodge.dto.ReviewDto;

public interface IReviewService {
    List<ReviewDto> getReviewsByUserId(Long userId);
    ReviewDto submitReview(ReviewDto reviewDto);
    void deleteReview(Long id);
}

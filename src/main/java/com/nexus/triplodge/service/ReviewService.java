package com.nexus.triplodge.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexus.triplodge.dto.ReviewDto;
import com.nexus.triplodge.model.Booking;
import com.nexus.triplodge.model.BookingStatus;
import com.nexus.triplodge.model.Review;
import com.nexus.triplodge.repository.BookingRepository;
import com.nexus.triplodge.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService implements IReviewService {
    private final ReviewRepository reviewRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ReviewDto> getReviewsByUserId(Long userId) {
        List<Review> reviews = reviewRepository.findReviewsByUserId(userId);
        return reviews.stream()
                      .map(review -> modelMapper.map(review, ReviewDto.class))
                      .collect(Collectors.toList());
    }

    @Override
    public ReviewDto submitReview(ReviewDto reviewDto) {
        Booking booking = bookingRepository.findById(reviewDto.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!booking.getStatus().equals(BookingStatus.COMPLETED)) {
            throw new RuntimeException("Cannot submit a review for an incomplete booking");
        }

        Review review = modelMapper.map(reviewDto, Review.class);

        review.setUser(booking.getGuest());

        review = reviewRepository.save(review);

        return modelMapper.map(review, ReviewDto.class);
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.findById(id).ifPresent(review -> reviewRepository.delete(review));
    }
}

package com.nexus.triplodge.dto;

import lombok.Data;

@Data
public class ReviewDto {
    private Long id;
    private Long bookingId;
    private Long userId;

    private int rating;
    private String reviewText;
}

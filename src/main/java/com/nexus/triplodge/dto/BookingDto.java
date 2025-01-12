package com.nexus.triplodge.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
public class BookingDto {
    private Long id;

    private Long guestId;

    private Long listingId;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    private String status;

    private BigDecimal totalPrice;

    private Long reviewId;

    private Long paymentId;
}

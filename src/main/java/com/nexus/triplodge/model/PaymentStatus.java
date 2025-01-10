package com.nexus.triplodge.model;

public enum PaymentStatus {
    PENDING,      // Payment is awaiting processing
    COMPLETED,    // Payment has been successfully completed
    FAILED,       // Payment attempt failed
    REFUNDED,     // Payment was refunded
    CANCELLED     // Payment was cancelled
}

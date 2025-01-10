package com.nexus.triplodge.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "listing")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Listing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "host_id")
    private User host;

    @OneToMany(mappedBy = "listing", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Booking> bookings = new HashSet<>();

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL)
    private Set<Photo> photos = new HashSet<>();

    @OneToMany(mappedBy = "listing", cascade = CascadeType.ALL)
    private Set<Availability> availabilities = new HashSet<>();

    private String title;
    private String description;
    private String location;
    private BigDecimal pricePerNight;
    private int maxGuest;

    @CreationTimestamp
    private Date createdAt = new Date();
}

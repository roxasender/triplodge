package com.nexus.triplodge.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String fullname;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserRole> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    private Set<Booking> bookings = new HashSet<>();

    @OneToMany(mappedBy = "host", cascade = CascadeType.ALL)
    private Set<Listing> listings = new HashSet<>();

    @OneToMany(mappedBy = "guest", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Messaging> messagings = new HashSet<>();

    // Need to validate url
    private String profilePictureUrl;

    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number")
    private String phoneNumber;

    @CreationTimestamp
    private Date createdAt = new Date();
}

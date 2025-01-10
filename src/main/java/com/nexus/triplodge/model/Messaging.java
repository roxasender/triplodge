package com.nexus.triplodge.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "messaging")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Messaging {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contentText;

    @CreationTimestamp
    private Date sentAt = new Date();

    @ManyToOne
    @JoinColumn(name = "user-id")
    private Set<User> users = new HashSet<>();
}

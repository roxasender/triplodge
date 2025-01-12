package com.nexus.triplodge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nexus.triplodge.model.Messaging;

public interface MessageRepository extends JpaRepository<Messaging, Long> {
    @Query(value = "SELECT * FROM messaging m " +
                   "JOIN user sender ON m.sender_id = sender.id " +
                   "JOIN user receiver ON m.receiver_id = receiver.id " +
                   "WHERE m.sender_id = :userId OR m.receiver_id = :userId " +
                   "ORDER BY m.sent_at ASC", nativeQuery = true)
    List<Messaging> findMessagesByUserId(@Param("userId") Long userId);
}

package com.nexus.triplodge.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexus.triplodge.dto.MessageDto;
import com.nexus.triplodge.model.Messaging;
import com.nexus.triplodge.service.IMessageService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {
    private final IMessageService messageService;

    // POST /api/messages: Sends a message to another user
    @PostMapping("/")
    public ResponseEntity<?> sendMessage(@RequestBody MessageDto messageDto) {
        try {
            MessageDto savedMessage = messageService.sendMessage(messageDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                Map.of("message", "Message sent successfully!", "data", savedMessage)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                Map.of("message", "Failed to send message: " + e.getMessage())
            );
        }
    }

    // GET /api/messages/conversation/{userId}: Retrieves messages for the user
    @GetMapping("/conversation/{userId}")
    public ResponseEntity<List<Messaging>> getMessagesForUser(@PathVariable Long userId) {
        List<Messaging> messages = messageService.getMessagesForUser(userId);
        if (messages.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(messages);
    } 
    
}

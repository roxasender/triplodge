package com.nexus.triplodge.service;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.nexus.triplodge.repository.MessageRepository;
import com.nexus.triplodge.repository.UserRepository;
import com.nexus.triplodge.dto.MessageDto;
import com.nexus.triplodge.model.Messaging;
import com.nexus.triplodge.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService implements IMessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public MessageDto sendMessage(MessageDto messageDto) {
        User sender = userRepository.findById(messageDto.getSenderId())
                .orElseThrow(() -> new IllegalArgumentException("Sender not found"));

        User recipient = userRepository.findById(messageDto.getRecipientId())
                .orElseThrow(() -> new IllegalArgumentException("Recipient not found"));

        Messaging message = modelMapper.map(messageDto, Messaging.class);
        message.setSender(sender);
        message.setReceiver(recipient);
        message.setSentAt(new Date());

        Messaging savedMessage = messageRepository.save(message);

        return modelMapper.map(savedMessage, MessageDto.class);
    }

    @Override
    public List<Messaging> getMessagesForUser(Long userId) {
        return messageRepository.findMessagesByUserId(userId);
    }
}

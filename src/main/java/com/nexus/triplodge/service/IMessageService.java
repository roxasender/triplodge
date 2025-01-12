package com.nexus.triplodge.service;

import java.util.List;

import com.nexus.triplodge.dto.MessageDto;
import com.nexus.triplodge.model.Messaging;

public interface IMessageService {
    MessageDto sendMessage(MessageDto messageDto);
    List<Messaging> getMessagesForUser(Long userId);
}

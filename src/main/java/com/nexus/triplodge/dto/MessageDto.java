package com.nexus.triplodge.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MessageDto {
    private Long id;

    private String contentText;

    private Date sentAt = new Date();

    private Long senderId;

    private Long recipientId;
}

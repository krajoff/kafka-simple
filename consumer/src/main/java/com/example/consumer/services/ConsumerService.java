package com.example.consumer.services;

import com.example.common.models.Message;
import com.example.consumer.repositories.MessageRepository;
import com.example.consumer.utils.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MessageMapper messageMapper;

    @Autowired
    public ConsumerService(MessageRepository messageRepository,
                           MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
    }

    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.group-id}")
    public void consumeMessage(@Payload Message message) {
        messageRepository.save(messageMapper.messageToMessageEntity(message));
    }
}


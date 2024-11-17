package com.example.consumer.services;

import com.example.consumer.models.Message;
import com.example.consumer.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {
    @Autowired
    private MessageRepository messageRepository;

    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.group-id}")
    public void consumeMessage(Message message) {
        messageRepository.save(message);
    }
}


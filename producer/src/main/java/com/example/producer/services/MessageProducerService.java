package com.example.producer.services;

import com.example.producer.models.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class MessageProducerService {

    private static final Logger logger = LoggerFactory.getLogger(MessageProducerService.class);
    private final KafkaTemplate<String, Message> kafkaTemplate;

    @Value("${kafka.topic}")
    private String topic;

    public MessageProducerService(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 60000)
    public void sendMessage() {
        Message message = new Message(UUID.randomUUID().toString(),
                "Message sent at " + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        kafkaTemplate.send(topic, message)
                .thenAccept(result -> logger.info("Message sent successfully: code={}, label={}, partition={}",
                        message.getCode(), message.getLabel(),
                        result.getRecordMetadata().partition()))
                .exceptionally(ex -> {
                    logger.error("Failed to send message: code={}, label={}",
                            message.getCode(), message.getLabel(), ex);
                    return null;
                });
    }
}

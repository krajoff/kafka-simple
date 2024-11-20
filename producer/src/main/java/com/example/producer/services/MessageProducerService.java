package com.example.producer.services;

import com.example.common.models.Message;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class MessageProducerService {

    private static final Logger logger = LoggerFactory
            .getLogger(MessageProducerService.class);

    private final KafkaTemplate<String, Message> kafkaTemplate;

    @Value("${kafka.topic}")
    private String topic;

    public MessageProducerService(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 1000)
    public void sendMessage() {

        Message message = createRandomMessage();
        sendToKafka(message)
                .thenAccept(result -> handleSuccess(message, result))
                .exceptionally(ex -> handleError(message, ex));
    }

    private Message createRandomMessage() {
        String code = UUID.randomUUID().toString();
        String label = "Сообщение отправлено "
                + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return new Message(code, label);
    }

    private CompletableFuture<SendResult<String, Message>> sendToKafka(Message message) {
        logger.debug("[Message] Отправка сообщения с кодом: {}", message.getCode());
        return kafkaTemplate.send(topic, message);
    }

    private void handleSuccess(Message message, SendResult<String, Message> result) {
        logger.info("[Message] Сообщение отправлено успешно: code={}, label={}, partition={}",
                message.getCode(), message.getLabel(),
                result.getRecordMetadata().partition());
    }

    private Void handleError(Message message, Throwable ex) {
        logger.error("[Message] Ошибка отправки сообщения: code={}, label={}",
                message.getCode(), message.getLabel(), ex);
        return null;
    }

}

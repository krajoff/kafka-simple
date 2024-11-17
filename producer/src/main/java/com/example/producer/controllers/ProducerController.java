package com.example.producer.controllers;

import com.example.producer.models.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/producer")
public class ProducerController {

    private final KafkaTemplate<String, Message> kafkaTemplate;

    public ProducerController(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Value("${kafka.topic}")
    private String topic;


    @PostMapping("/send")
    public ResponseEntity<String> sendMessage(@RequestBody Message message) {
        kafkaTemplate.send(topic, message);
        return ResponseEntity.ok("Message sent to Kafka");
    }

    @PostMapping("/send-multiple")
    public ResponseEntity<String> sendMultipleMessages(@RequestBody List<Message> messages) {
        messages.forEach(message -> kafkaTemplate.send(topic, message));
        return ResponseEntity.ok("Multiple messages sent to Kafka");
    }
}

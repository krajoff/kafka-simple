package com.example.uiback.exceptions;

public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException() {
        this("[Message Kafka] Message not found");
    }

    public MessageNotFoundException(String message) {
        super(message);
    }
}

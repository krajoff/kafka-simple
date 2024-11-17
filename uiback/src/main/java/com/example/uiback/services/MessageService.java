package com.example.uiback.services;

import com.example.uiback.models.Message;

import java.util.List;

public interface MessageService {
    Message getMessageById(Long id);

    List<Message> getAllMessages();

    Message createMessage(Message message);

    Message updateMessage(Long id, Message message);

    void deleteMessageById(Long id);

}

package com.example.uiback.services;

import com.example.uiback.exceptions.MessageNotFoundException;
import com.example.uiback.models.Message;
import com.example.uiback.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Override
    public Message getMessageById(Long id) {
        return messageRepository.findById(id)
                .orElseThrow(MessageNotFoundException::new);
    }

    @Override
    public List<Message> getMessages(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return messageRepository.findAll(pageable).getContent();
    }

    @Override
    public Message createMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message updateMessage(Long id, Message message) {
        var existingMessage = getMessageById(id);
        existingMessage.setCode(message.getCode());
        existingMessage.setLabel(message.getLabel());
        return createMessage(existingMessage);
    }

    @Override
    public void deleteMessageById(Long id) {
        messageRepository.deleteById(id);
    }
}

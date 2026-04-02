package com.example.controller;

import com.example.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    @MessageMapping("/send")
    public void sendMessage(Message message) {
        message.setTimestamp(System.currentTimeMillis());
        kafkaTemplate.send("raw-messages", message.getUser(), message);
    }
}

package com.example.bridge;

import com.example.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaWebBridge {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics = "clean-messages", groupId = "web-bridge-group")
    public void listen(Message message) {
        messagingTemplate.convertAndSend("/topic/messages", message);
    }
}

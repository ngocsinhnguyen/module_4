package com.example.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.io.IOException;
import java.util.Map;

public class MessageSerde implements Serde<Message> {
    
    public static class MessageSerializer implements Serializer<Message> {
        private final ObjectMapper objectMapper = new ObjectMapper();
        @Override
        public byte[] serialize(String topic, Message data) {
            try {
                return objectMapper.writeValueAsBytes(data);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static class MessageDeserializer implements Deserializer<Message> {
        private final ObjectMapper objectMapper = new ObjectMapper();
        @Override
        public Message deserialize(String topic, byte[] data) {
            try {
                return objectMapper.readValue(data, Message.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Serializer<Message> serializer() {
        return new MessageSerializer();
    }

    @Override
    public Deserializer<Message> deserializer() {
        return new MessageDeserializer();
    }

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {}

    @Override
    public void close() {}
}

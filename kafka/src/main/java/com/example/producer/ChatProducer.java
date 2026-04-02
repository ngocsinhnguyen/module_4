package com.example.producer;

import com.example.entity.Message;
import com.example.entity.MessageSerde;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Scanner;

public class ChatProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, MessageSerde.MessageSerializer.class);

        KafkaProducer<String, Message> producer = new KafkaProducer<>(props);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Chat Producer Started. Enter user name:");
        String user = scanner.nextLine();

        System.out.println("Start typing messages (type 'exit' to quit):");
        while (true) {
            String content = scanner.nextLine();
            if ("exit".equalsIgnoreCase(content)) break;

            Message msg = new Message(user, content, System.currentTimeMillis());
            producer.send(new ProducerRecord<>("raw-messages", user, msg));
            System.out.println("Sent: " + msg);
        }

        producer.close();
        scanner.close();
    }
}

package com.example.producer;

import com.example.entity.Message;
import com.example.entity.MessageSerde;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LoadGenerator {
    private static final String[] USERS = {"Alice", "Bob", "Charlie", "David", "Eve"};
    private static final String[] QUOTES = {
        "Hello everyone!",
        "This is a badword message.",
        "Check out this great quảng cáo!",
        "Kafka Streams is awesome.",
        "Stop sending spam in this chat.",
        "Normal message here.",
        "Another thô tục content to filter."
    };

    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, MessageSerde.MessageSerializer.class);
        props.put(ProducerConfig.LINGER_MS_CONFIG, 1); // Low latency

        KafkaProducer<String, Message> producer = new KafkaProducer<>(props);
        int threads = 50; //  50 luồng chạy song song
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        Random random = new Random();

        System.out.println("Starting High Load Generator with " + threads + " threads...");

        for (int i = 0; i < threads; i++) {
            executor.submit(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    String user = USERS[random.nextInt(USERS.length)];
                    String content = QUOTES[random.nextInt(QUOTES.length)];
                    Message msg = new Message(user, content, System.currentTimeMillis());
                    producer.send(new ProducerRecord<>("raw-messages", user, msg));
                }
            });
        }

        TimeUnit.SECONDS.sleep(30); // Chạy trong 30 giây
        executor.shutdownNow();
        producer.close();
        System.out.println("High Load Generator Stopped.");
    }
}

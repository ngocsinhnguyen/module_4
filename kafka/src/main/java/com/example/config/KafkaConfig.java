package com.example.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic rawMessagesTopic() {
        return TopicBuilder.name("raw-messages")
                .partitions(6)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic cleanMessagesTopic() {
        return TopicBuilder.name("clean-messages")
                .partitions(6)
                .replicas(1)
                .build();
    }
}

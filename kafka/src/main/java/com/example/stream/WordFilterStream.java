package com.example.stream;

import com.example.entity.Message;
import com.example.entity.MessageSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class WordFilterStream {
    private static final List<String> BLACKLIST = Arrays.asList("spam", "badword", "thô tục", "quảng cáo");

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "word-filter-app");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, MessageSerde.class);

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, Message> rawMessages = builder.stream("raw-messages", Consumed.with(Serdes.String(), new MessageSerde()));

        KStream<String, Message> cleanMessages = rawMessages.mapValues(message -> {
            String content = message.getContent();
            for (String badWord : BLACKLIST) {
                if (content.toLowerCase().contains(badWord.toLowerCase())) {
                    content = content.replaceAll("(?i)" + badWord, "***");
                }
            }
            message.setContent(content);
            return message;
        });

        cleanMessages.to("clean-messages", Produced.with(Serdes.String(), new MessageSerde()));

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
        System.out.println("Kafka Streams Word Filter Started...");
    }
}

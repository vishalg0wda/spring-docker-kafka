package com.example.springdockerkafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;

@SpringBootApplication
@EnableKafka
public class Svc1Application {

    Logger LOG = LoggerFactory.getLogger(Svc1Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Svc1Application.class, args);
    }

    @Value("${spring.application.name}")
    private String appName;

    @KafkaListener(topics = "foo")
    @SendTo("bar")
    public String process(String msg) {
        LOG.info("Processing message: {}", msg);
        return String.format("[%s] processed: %s", appName, msg);
    }
}

package com.example.svc2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;

@SpringBootApplication
public class Svc2Application {

    Logger LOG = LoggerFactory.getLogger(Svc2Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Svc2Application.class, args);
    }

    @Value("${spring.application.name}")
    private String appName;

    @KafkaListener(topics = "bar")
    @SendTo("baz")
    public String process(String msg) {
        LOG.info("Processing message: {}", msg);
        return String.format("[%s] processed: %s", appName, msg);
    }
}

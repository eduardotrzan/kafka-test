package com.kafka.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.kafka.test.config.ApplicationPropConfig;
import com.kafka.test.config.KafkaPropConfig;
import com.kafka.test.event.GreetingEvent;
import com.kafka.test.listener.ListenerConfig;
import com.kafka.test.service.MessageProducer;

@Slf4j
@RequiredArgsConstructor
@EnableConfigurationProperties({
                                       ApplicationPropConfig.class,
                                       KafkaPropConfig.class
                               })
@SpringBootApplication
public class Application implements CommandLineRunner {

    private final ApplicationPropConfig config;

    private final MessageProducer producer;

    private final ListenerConfig listenerConfig;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @Override
    public void run(String... strings) {
        log.info("Running system with config={}.", this.config);
        this.runEvent();
    }

    private void runEvent() {
        try {
            this.producer.sendGreetingMessage(new GreetingEvent("Greetings", "World New approach!"));
            this.listenerConfig.getGreetingLatch().await(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Error happened while running event.", e);
            throw new RuntimeException(e);
        }
    }

}

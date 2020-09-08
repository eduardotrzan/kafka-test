package com.kafka.test.service.config;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import com.kafka.test.service.event.KafkaAutoConfigurer;
import com.kafka.test.service.event.listener.PersonCreatedEventListener;
import com.kafka.test.service.event.listener.PersonGreetingEventListener;
import com.kafka.test.service.event.message.GreetingEvent;
import com.kafka.test.service.event.message.PersonCreatedEvent;

@Slf4j
@Configuration
public class KafkaServerConfig {

    public KafkaServerConfig(KafkaAutoConfigurer kafkaAutoConfigurer) {
        kafkaAutoConfigurer.createTopic(GreetingEvent.class, PersonCreatedEvent.class);
        kafkaAutoConfigurer.configureProducer(GreetingEvent.class, PersonCreatedEvent.class);

        kafkaAutoConfigurer.configureConsumer(PersonGreetingEventListener.class, GreetingEvent.class);
        kafkaAutoConfigurer.configureConsumer(PersonCreatedEventListener.class, PersonCreatedEvent.class);

        kafkaAutoConfigurer.createTemplate(GreetingEvent.class, PersonCreatedEvent.class);
    }
}

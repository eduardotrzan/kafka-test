package com.kafka.test.service.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import com.kafka.test.service.event.message.GreetingEvent;

@Slf4j
@RequiredArgsConstructor
@Component
public class MessageProducer {

    private final KafkaTemplateManager kafkaTemplateManager;

    public void sendGreetingMessage(GreetingEvent greeting) {
        this.kafkaTemplateManager
                .getKafkaTemplate(GreetingEvent.class)
                .send(GreetingEvent.TOPIC, greeting);
    }
}

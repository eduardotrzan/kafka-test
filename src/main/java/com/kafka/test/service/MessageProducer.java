package com.kafka.test.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import com.kafka.test.event.GreetingEvent;
import com.kafka.test.template.GreetingKafkaTemplate;

@Slf4j
@RequiredArgsConstructor
@Component
public class MessageProducer {

    private final GreetingKafkaTemplate greetingKafkaTemplate;

    public void sendGreetingMessage(GreetingEvent greeting) {
        greetingKafkaTemplate.send(GreetingEvent.EVENT_NAME, greeting);
    }
}

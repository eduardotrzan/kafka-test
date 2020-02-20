package com.kafka.test.service.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import com.kafka.test.service.event.message.GreetingEvent;
import com.kafka.test.service.event.template.GreetingKafkaTemplate;

@Slf4j
@RequiredArgsConstructor
@Component
public class MessageProducer {

    private final GreetingKafkaTemplate greetingKafkaTemplate;

    public void sendGreetingMessage(GreetingEvent greeting) {
        this.greetingKafkaTemplate.send(GreetingEvent.EVENT_NAME, greeting);
    }
}

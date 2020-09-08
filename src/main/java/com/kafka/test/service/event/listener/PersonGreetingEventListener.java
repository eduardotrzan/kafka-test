package com.kafka.test.service.event.listener;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import com.kafka.test.annotation.GenericKafkaListener;
import com.kafka.test.service.event.message.GreetingEvent;

@Slf4j
@Component
public class PersonGreetingEventListener {

    @GenericKafkaListener(topic = GreetingEvent.TOPIC,
                          consumerGroupId = GreetingEvent.CONSUMER_GROUP_ID,
                          consumerName = GreetingEvent.CONSUMER_NAME)
    public void handle(GreetingEvent event) {
        log.info("NEW EVENT TYPE - GREETING - Received event message: {}.", event);
    }

}

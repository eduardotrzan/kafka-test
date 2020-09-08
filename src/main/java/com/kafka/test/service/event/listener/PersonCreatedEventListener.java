package com.kafka.test.service.event.listener;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

import org.springframework.stereotype.Component;

import com.kafka.test.annotation.GenericKafkaListener;
import com.kafka.test.service.event.message.PersonCreatedEvent;

@Slf4j
@Component
public class PersonCreatedEventListener {

    private CountDownLatch greetingLatch = new CountDownLatch(1);

    @GenericKafkaListener(topic = PersonCreatedEvent.TOPIC,
                          consumerGroupId = PersonCreatedEvent.CONSUMER_GROUP_ID,
                          consumerName = PersonCreatedEvent.CONSUMER_NAME)
    public void handle(PersonCreatedEvent event) {
        log.info("NEW EVENT TYPE - Received event message: {}.", event);
        this.greetingLatch.countDown();
    }

}

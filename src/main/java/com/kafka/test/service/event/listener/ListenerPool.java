package com.kafka.test.service.event.listener;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.kafka.test.service.business.EmailService;
import com.kafka.test.service.event.message.GreetingEvent;
import com.kafka.test.service.event.message.PersonCreatedEvent;

@RequiredArgsConstructor
@Getter
@Slf4j
@Component
public class ListenerPool {

    private final EmailService emailService;

    private CountDownLatch greetingLatch = new CountDownLatch(1);

    @KafkaListener(topics = GreetingEvent.EVENT_NAME, containerFactory = GreetingListener.LISTENER_NAME)
    public void greetingListener(GreetingEvent event) {
        log.info("Received event message: {}.", event);
        this.greetingLatch.countDown();
    }

    @KafkaListener(topics = PersonCreatedEvent.EVENT_NAME, containerFactory = PersonCreatedListener.LISTENER_NAME)
    public void personCreatedListener(PersonCreatedEvent event) {
        String content = String.format("Welcome %s!", event.getName());
        log.info("Created greeting as content={} and fake email successfully sent", content);
        this.emailService.addEmailToPersonUuid(event.getUuid(), content);
    }

}

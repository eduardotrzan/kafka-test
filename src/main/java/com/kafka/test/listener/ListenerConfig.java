package com.kafka.test.listener;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.kafka.test.event.GreetingEvent;

@Getter
@Slf4j
@Component
public class ListenerConfig {

    private CountDownLatch greetingLatch = new CountDownLatch(1);

    @KafkaListener(topics = GreetingEvent.EVENT_NAME, containerFactory = GreetingListener.LISTENER_NAME)
    public void greetingListener(GreetingEvent greeting) {
        log.info("Received greeting message: {}.", greeting);
        this.greetingLatch.countDown();
    }

}

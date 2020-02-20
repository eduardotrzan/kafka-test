package com.kafka.test.listener;

import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.stereotype.Component;

import com.kafka.test.consumer.GreetingConsumer;
import com.kafka.test.event.GreetingEvent;

@Component(value = GreetingListener.LISTENER_NAME)
class GreetingListener extends ConcurrentKafkaListenerContainerFactory<String, GreetingEvent> {

    static final String LISTENER_NAME = "GreetingListener";

    GreetingListener(GreetingConsumer greetingConsumer) {
        super();
        super.setConsumerFactory(greetingConsumer.getConsumerFactory());
    }
}

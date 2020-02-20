package com.kafka.test.service.event.listener;

import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.stereotype.Component;

import com.kafka.test.service.event.consumer.GreetingConsumer;
import com.kafka.test.service.event.message.GreetingEvent;

@Component(value = GreetingListener.LISTENER_NAME)
class GreetingListener extends ConcurrentKafkaListenerContainerFactory<String, GreetingEvent> {

    static final String LISTENER_NAME = "GreetingListener";

    GreetingListener(GreetingConsumer consumer) {
        super();
        super.setConsumerFactory(consumer.getConsumerFactory());
    }
}

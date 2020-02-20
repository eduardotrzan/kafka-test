package com.kafka.test.service.event.listener;

import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.stereotype.Component;

import com.kafka.test.service.event.consumer.PersonCreatedConsumer;
import com.kafka.test.service.event.message.PersonCreatedEvent;

@Component(value = PersonCreatedListener.LISTENER_NAME)
class PersonCreatedListener extends ConcurrentKafkaListenerContainerFactory<String, PersonCreatedEvent> {

    static final String LISTENER_NAME = "PersonCreatedListener";

    PersonCreatedListener(PersonCreatedConsumer consumer) {
        super();
        super.setConsumerFactory(consumer.getConsumerFactory());
    }
}

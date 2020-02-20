package com.kafka.test.service.event.template;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.kafka.test.service.event.message.PersonCreatedEvent;
import com.kafka.test.service.event.producer.PersonCreatedProducer;

@Component
public class PersonCreatedKafkaTemplate extends KafkaTemplate<String, PersonCreatedEvent> {

    public PersonCreatedKafkaTemplate(PersonCreatedProducer producer) {
        super(producer.getProducerFactory());
    }

}

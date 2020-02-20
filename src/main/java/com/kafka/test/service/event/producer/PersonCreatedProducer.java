package com.kafka.test.service.event.producer;

import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;

import com.kafka.test.service.config.KafkaPropConfig;
import com.kafka.test.service.event.message.PersonCreatedEvent;

@Component
public class PersonCreatedProducer extends AbstractKafkaProducer<PersonCreatedEvent> {

    public PersonCreatedProducer(KafkaPropConfig kafkaPropConfig) {
        super(kafkaPropConfig);
    }

    @Override
    protected JsonSerializer<PersonCreatedEvent> getJsonSerializer() {
        return new JsonSerializer<>();
    }
}

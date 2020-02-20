package com.kafka.test.service.event.consumer;

import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;

import com.kafka.test.service.config.KafkaPropConfig;
import com.kafka.test.service.event.message.PersonCreatedEvent;

@Component
public class PersonCreatedConsumer extends AbstractKafkaConsumer<PersonCreatedEvent> {

    PersonCreatedConsumer(KafkaPropConfig kafkaPropConfig) {
        super(kafkaPropConfig);
    }

    @Override
    protected String getGroupId() {
        return PersonCreatedEvent.EVENT_NAME;
    }

    @Override
    protected Deserializer<PersonCreatedEvent> getDeserializer() {
        return new JsonDeserializer<>(PersonCreatedEvent.class);
    }
}

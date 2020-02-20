package com.kafka.test.service.event.consumer;

import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;

import com.kafka.test.service.config.KafkaPropConfig;
import com.kafka.test.service.event.message.GreetingEvent;

@Component
public class GreetingConsumer extends AbstractKafkaConsumer<GreetingEvent> {

    GreetingConsumer(KafkaPropConfig kafkaPropConfig) {
        super(kafkaPropConfig);
    }

    @Override
    protected String getGroupId() {
        return GreetingEvent.EVENT_NAME;
    }

    @Override
    protected Deserializer<GreetingEvent> getDeserializer() {
        return new JsonDeserializer<>(GreetingEvent.class);
    }
}

package com.kafka.test.consumer;

import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.stereotype.Component;

import com.kafka.test.config.KafkaPropConfig;
import com.kafka.test.event.GreetingEvent;

@Component
public class GreetingConsumer extends AbstractKafkaConsumer<GreetingEvent> {

    private static final String GROUP_ID = "greeting";

    GreetingConsumer(KafkaPropConfig kafkaPropConfig) {
        super(kafkaPropConfig);
    }

    @Override
    protected String getGroupId() {
        return GROUP_ID;
    }

    @Override
    protected Deserializer<GreetingEvent> getDeserializer() {
        return new JsonDeserializer<>(GreetingEvent.class);
    }
}

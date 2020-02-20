package com.kafka.test.producer;

import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;

import com.kafka.test.config.KafkaPropConfig;
import com.kafka.test.event.GreetingEvent;

@Component
public class GreetingProducer extends AbstractKafkaProducer<GreetingEvent> {

    public GreetingProducer(KafkaPropConfig kafkaPropConfig) {
        super(kafkaPropConfig);
    }

    @Override
    protected JsonSerializer<GreetingEvent> getJsonSerializer() {
        return new JsonSerializer<>();
    }
}

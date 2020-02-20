package com.kafka.test.service.event.template;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.kafka.test.service.event.message.GreetingEvent;
import com.kafka.test.service.event.producer.GreetingProducer;

@Component
public class GreetingKafkaTemplate extends KafkaTemplate<String, GreetingEvent> {

    public GreetingKafkaTemplate(GreetingProducer producer) {
        super(producer.getProducerFactory());
    }

}

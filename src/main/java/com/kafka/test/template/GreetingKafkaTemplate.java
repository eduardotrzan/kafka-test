package com.kafka.test.template;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.kafka.test.event.GreetingEvent;
import com.kafka.test.producer.GreetingProducer;

@Component
public class GreetingKafkaTemplate extends KafkaTemplate<String, GreetingEvent> {

    public GreetingKafkaTemplate(GreetingProducer greetingProducer) {
        super(greetingProducer.getProducerFactory());
    }

}

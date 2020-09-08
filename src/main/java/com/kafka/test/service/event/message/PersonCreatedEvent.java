package com.kafka.test.service.event.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

import com.kafka.test.annotation.GenericKafkaEvent;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = { "uuid", "name" })
@GenericKafkaEvent(topic = PersonCreatedEvent.TOPIC,
                   producerName = PersonCreatedEvent.PRODUCER_NAME,
                   templateName = PersonCreatedEvent.TEMPLATE_NAME)
public class PersonCreatedEvent {

    public static final String TOPIC = "person.created.event";
    public static final String CONSUMER_GROUP_ID = "PersonCreatedEvent";
    public static final String CONSUMER_NAME = "PersonCreatedEventConsumer";
    public static final String PRODUCER_NAME = "PersonCreatedEventProducer";
    public static final String TEMPLATE_NAME = "PersonCreatedEvenTemplate";

    private UUID uuid;

    private String name;
}

package com.kafka.test.service.event.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

import com.kafka.test.annotation.KafkaEvent;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = { "uuid", "name" })
@KafkaEvent(topic = PersonCreatedEvent.EVENT_NAME)
public class PersonCreatedEvent {

    public static final String EVENT_NAME = "person.created.event";

    private UUID uuid;

    private String name;
}

package com.kafka.test.service.event.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.kafka.test.annotation.GenericKafkaEvent;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = { "msg", "name" })
@GenericKafkaEvent(topic = GreetingEvent.TOPIC,
                   producerName = GreetingEvent.PRODUCER_NAME,
                   templateName = GreetingEvent.TEMPLATE_NAME)
public class GreetingEvent {

    public static final String TOPIC = "greeting.created.event";
    public static final String CONSUMER_GROUP_ID = "PersonGreetingEvent";
    public static final String CONSUMER_NAME = "PersonGreetingEventConsumer";
    public static final String PRODUCER_NAME = "PersonGreetingEventProducer";
    public static final String TEMPLATE_NAME = "PersonGreetingEventTemplate";

    private String msg;
    private String name;
}

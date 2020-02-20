package com.kafka.test.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.kafka.test.annotation.KafkaEvent;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = { "msg", "name" })
@KafkaEvent(topic = GreetingEvent.EVENT_NAME)
public class GreetingEvent {

    public static final String EVENT_NAME = "greeting";

    private String msg;
    private String name;
}

package com.kafka.test.consumer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import com.kafka.test.config.KafkaPropConfig;

abstract class AbstractKafkaConsumer<T> {

    private final Map<String, Object> configProps;

    AbstractKafkaConsumer(KafkaPropConfig kafkaPropConfig) {
        this.configProps = new HashMap<>();
        this.configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaPropConfig.getBootstrapAddress());
        this.configProps.put(ConsumerConfig.GROUP_ID_CONFIG, this.getGroupId());
    }

    public ConsumerFactory<String, T> getConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(this.configProps, new StringDeserializer(), this.getDeserializer());
    }

    protected abstract String getGroupId();
    protected abstract Deserializer<T> getDeserializer();
}

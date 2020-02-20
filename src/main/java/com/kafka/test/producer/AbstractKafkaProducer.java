package com.kafka.test.producer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.kafka.test.config.KafkaPropConfig;

abstract class AbstractKafkaProducer<T> {

    private final Map<String, Object> configProps;

    AbstractKafkaProducer(KafkaPropConfig kafkaPropConfig) {
        this.configProps = new HashMap<>();
        this.configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaPropConfig.getBootstrapAddress());
        this.configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    }

    public ProducerFactory<String, T> getProducerFactory() {
        this.configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, getJsonSerializer().getClass());
        return new DefaultKafkaProducerFactory<>(this.configProps);
    }

    protected abstract JsonSerializer<T> getJsonSerializer();
}

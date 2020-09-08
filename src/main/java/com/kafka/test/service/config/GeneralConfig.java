package com.kafka.test.service.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.context.annotation.Import;

@Import({ KafkaServerConfig.class, SpringSecurityConfig.class })
@EnableAutoConfiguration(exclude = KafkaAutoConfiguration.class)
public class GeneralConfig {

}

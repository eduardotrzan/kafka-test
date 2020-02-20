package com.kafka.test.service.config;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.errors.TopicExistsException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.kafka.core.KafkaAdmin;

import com.kafka.test.annotation.KafkaEvent;

@Slf4j
@Configuration
public class KafkaServerConfig {

    @Bean
    public KafkaAdmin kafkaAdmin(KafkaPropConfig kafkaPropConfig) {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaPropConfig.getBootstrapAddress());
        return new KafkaAdmin(configs);
    }

    @Bean
    public AdminClient adminClient(KafkaPropConfig kafkaPropConfig) {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaPropConfig.getBootstrapAddress());
        configs.put(AdminClientConfig.CLIENT_ID_CONFIG, "new-topics-client");
        AdminClient adminClient = KafkaAdminClient.create(configs);
        this.autoConfigureTopics(adminClient);
        return adminClient;
    }

    private void autoConfigureTopics(AdminClient adminClient) {
        Set<BeanDefinition> annotatedKafkaEvents = this.findAnnotatedKafkaEvents("com.kafka.test.service.event");
        annotatedKafkaEvents.forEach(b -> this.createTopic(adminClient, b));
    }

    private Set<BeanDefinition> findAnnotatedKafkaEvents(String scanPackage) {
        ClassPathScanningCandidateComponentProvider provider
                = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(KafkaEvent.class));
        return provider.findCandidateComponents(scanPackage);
    }

    private void createTopic(AdminClient adminClient, BeanDefinition beanDefinition) {
        try {
            Class<?> clazz = Class.forName(beanDefinition.getBeanClassName());
            KafkaEvent KafkaEvent = clazz.getAnnotation(KafkaEvent.class);
            String topic = KafkaEvent.topic();
            log.debug("Found class={} with topic={}", clazz.getSimpleName(), KafkaEvent.topic());
            addTopicInKafka(adminClient, topic);
        } catch (Exception e) {
            log.error("Exception happened for beanDefinition={}.", beanDefinition, e);
        }
    }

    private void addTopicInKafka(AdminClient adminClient, String topic) {
        try {
            final NewTopic newTopic = new NewTopic(topic, 1, (short) 1);

            // Create topic, which is async call.
            final CreateTopicsResult createTopicsResult = adminClient.createTopics(Collections.singleton(newTopic));

            // Since the call is Async, Lets wait for it to complete.
            createTopicsResult.values().get(topic).get();

            log.info("Created new topic={} with numPartitions=1 and replicationFactor=1.", topic);
        } catch (InterruptedException | ExecutionException e) {
            if (!(e.getCause() instanceof TopicExistsException)) {
                throw new RuntimeException(e.getMessage(), e);
            }

            log.debug("Topic={} already exists.", topic);
        }
    }
}

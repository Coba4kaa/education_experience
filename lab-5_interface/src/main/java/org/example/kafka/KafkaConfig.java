package org.example.kafka;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

@Configuration
public class KafkaConfig {
    @Bean
    public ReplyingKafkaTemplate<String, Object, Object> catReplyKafkaTemplate(ProducerFactory<String, Object> pf, @Qualifier("catsRepliesContainer") ConcurrentMessageListenerContainer<String, Object> catRepliesContainer) {
        return new ReplyingKafkaTemplate<>(pf, catRepliesContainer);
    }

    @Bean
    public ReplyingKafkaTemplate<String, Object, Object> ownerReplyKafkaTemplate(ProducerFactory<String, Object> pf, @Qualifier("ownerRepliesContainer") ConcurrentMessageListenerContainer<String, Object> ownerRepliesContainer) {
        return new ReplyingKafkaTemplate<>(pf, ownerRepliesContainer);
    }

    @Bean("catsRepliesContainer")
    public ConcurrentMessageListenerContainer<String, Object> catsRepliesContainer(
            ConcurrentKafkaListenerContainerFactory<String, Object> containerFactory) {
        ConcurrentMessageListenerContainer<String, Object> repliesContainer = containerFactory.createContainer("cats_response");
        repliesContainer.getContainerProperties().setGroupId("group_id");
        repliesContainer.setAutoStartup(false);
        return repliesContainer;
    }

    @Bean("ownerRepliesContainer")
    public ConcurrentMessageListenerContainer<String, Object> ownerRepliesContainer(
            ConcurrentKafkaListenerContainerFactory<String, Object> containerFactory) {
        ConcurrentMessageListenerContainer<String, Object> repliesContainer = containerFactory.createContainer("owners_response");
        repliesContainer.getContainerProperties().setGroupId("group_id");
        repliesContainer.setAutoStartup(false);
        return repliesContainer;
    }
}


package com.example.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic topic1(){
        return new NewTopic("cats_request", 1, (short) 1);
    }

    @Bean
    public NewTopic topic2(){
        return new NewTopic("cats_response", 1, (short) 1);
    }

    @Bean
    public NewTopic topic3(){
        return new NewTopic("all_cats_request", 1, (short) 1);
    }
}

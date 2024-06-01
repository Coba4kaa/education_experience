package com.example.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic topic1(){
        return new NewTopic("owners_request", 1, (short) 1);
    }

    @Bean
    public NewTopic topic2(){
        return new NewTopic("owners_response", 1, (short) 1);
    }

    @Bean
    public NewTopic topic3(){
        return new NewTopic("owner_by_name_request", 1, (short) 1);
    }

    @Bean
    public NewTopic topic4(){
        return new NewTopic("all_owners_request", 1, (short) 1);
    }
}

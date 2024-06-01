package org.example.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class KafkaService {
    @Autowired
    @Qualifier("catReplyKafkaTemplate")
    private ReplyingKafkaTemplate<String, Object, Object> catReplyTemplate;

    @Autowired
    @Qualifier("ownerReplyKafkaTemplate")
    private ReplyingKafkaTemplate<String, Object, Object> ownerReplyTemplate;

    public Object kafkaRequestReply(String topic, String request) throws Exception {
        ReplyingKafkaTemplate<String, Object, Object> template;
        if ("cats_request".equals(topic) || "all_cats_request".equals(topic)) {
            template = catReplyTemplate;
        } else if ("owners_request".equals(topic) || "owner_by_name_request".equals(topic) || "all_owners_request".equals(topic)) {
            template = ownerReplyTemplate;
        } else {
            throw new IllegalArgumentException("Unknown topic: " + topic);
        }

        ProducerRecord<String, Object> record = new ProducerRecord<>(topic, request);
        RequestReplyFuture<String, Object, Object> replyFuture = template.sendAndReceive(record);
        SendResult<String, Object> sendResult = replyFuture.getSendFuture().get(10, TimeUnit.SECONDS);
        ConsumerRecord<String, Object> consumerRecord = replyFuture.get(10, TimeUnit.SECONDS);
        return consumerRecord.value();
    }
}

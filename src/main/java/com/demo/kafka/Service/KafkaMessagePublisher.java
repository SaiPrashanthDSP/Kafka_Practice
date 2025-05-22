package com.demo.kafka.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
@Service
public class KafkaMessagePublisher {
    @Autowired
    private KafkaTemplate<String,Object> template;

    public void sendMessageToTopic(String message) {
        CompletableFuture<SendResult<String, Object>> future =  template.send("sample-topic",message);
        future.whenComplete((result,ex) -> {
                if(ex == null) {
                    System.out.println(message + "With Offset ="+ result.getRecordMetadata().offset());
                    }
                else {
                    System.out.println("Unable to send message due to "+ex.getMessage());
                }
        });
    }
}

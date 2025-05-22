package com.demo.kafka.Controller;

import com.demo.kafka.DTO.Data;
import com.demo.kafka.Service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer-app")
public class EventController {

    @Autowired
    private KafkaMessagePublisher publisher;
    @GetMapping("/publish/{message}")
    public ResponseEntity<?> publishMessage(@RequestBody Data message) {
        try {
            publisher.sendMessageToTopic(message.getTopicName(),message.getMessage());
            return  ResponseEntity.ok("Message Published Successfully");
        } catch (Exception e) {
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

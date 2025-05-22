package com.demo.kafka.Service;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.errors.TopicExistsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

@Service
public class TopicManagerService {


        @Value("${spring.kafka.bootstrap-servers}")
        private String bootstrapServers;

        public void createTopicIfNotExists(String topicName, int partitions, short replicationFactor) {
            Properties props = new Properties();
            props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

            try (AdminClient adminClient = AdminClient.create(props)) {
                NewTopic newTopic = new NewTopic(topicName, partitions, replicationFactor);

                CreateTopicsResult result = adminClient.createTopics(Collections.singleton(newTopic));

                result.values().get(topicName).get(); // wait for creation

                System.out.println("Topic created: " + topicName);
            } catch (ExecutionException e) {
                if (e.getCause() instanceof TopicExistsException) {
                    System.out.println("Topic already exists: " + topicName);
                } else {
                    throw new RuntimeException("Failed to create topic", e);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Topic creation interrupted", e);
            }
        }
}

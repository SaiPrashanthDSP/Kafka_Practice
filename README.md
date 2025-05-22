# 🧵 Spring Boot Kafka Producer Service

This is a simple **Kafka Producer** service built using **Spring Boot**. It demonstrates how to produce messages to an Apache Kafka topic using Spring Kafka.

---

## 🚀 Features

- Produce messages to Kafka using Spring Kafka
- Configurable Kafka topic and bootstrap server
- REST endpoint to send messages
- Structured logging and exception handling

---

## 🛠 Tech Stack

- Java 17+ (or 11+)
- Spring Boot (3.x or 2.x)
- Spring Kafka
- Apache Kafka

---

## 📦 Requirements

- Apache Kafka and Zookeeper (running locally or via Docker)
- Java 17+ installed
- Maven or Gradle

---

## ⚙️ Configuration

Configure Kafka in `application.yml` or `application.properties`:

```yaml
spring:
  kafka:
    bootstrap-servers: localhost:9092
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.apache.kafka.common.serialization.StringSerializer
kafka:
  topic: sample-topic

package com.kafka_village_j.kafka;

import com.kafka_village_j.config.domain.dto.RequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {
    @Value("${spring.kafka.consumer.bootstrap-servers}")
    String bootstrapServers;
    @Value("${spring.kafka.consumer.key-deserializer}")
    String keySerializer;
    @Value("${spring.kafka.consumer.value-deserializer}")
    String valueSerializer;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public Boolean sendMessage(String topic, String message) {
        CompletableFuture<SendResult<String, String>> result = kafkaTemplate.send(topic, message);
        kafkaTemplate.flush();
        return result.handle((sendResult, throwable) -> throwable == null ? true : false).join();
    }
}
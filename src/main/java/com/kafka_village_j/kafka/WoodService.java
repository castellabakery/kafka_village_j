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
public class WoodService {

    private final KafkaProducerService kafkaProducerService;

    private final String CREATE_TOPIC = "CREATE";
    private final String UPDATE_TOPIC = "UPDATE";
    private final String DELETE_TOPIC = "DELETE";

    public Boolean create(String message) {
        return kafkaProducerService.sendMessage(CREATE_TOPIC, message);
    }

    public Boolean update(String message) {
        return kafkaProducerService.sendMessage(UPDATE_TOPIC, message);
    }

    public Boolean delete(String message) {
        return kafkaProducerService.sendMessage(DELETE_TOPIC, message);
    }
}
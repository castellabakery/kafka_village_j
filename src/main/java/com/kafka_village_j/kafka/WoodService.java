package com.kafka_village_j.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WoodService {

    private final KafkaProducerService kafkaProducerService;

    private final String CREATE_TOPIC = "CREATE";
    private final String UPDATE_TOPIC = "UPDATE";
    private final String DELETE_TOPIC = "DELETE";

    public Boolean create(JsonNode message) {
        return kafkaProducerService.sendMessage(CREATE_TOPIC, message.toString());
    }

    public Boolean update(JsonNode message) {
        return kafkaProducerService.sendMessage(UPDATE_TOPIC, message.toString());
    }

    public Boolean delete(JsonNode message) {
        return kafkaProducerService.sendMessage(DELETE_TOPIC, message.toString());
    }
}
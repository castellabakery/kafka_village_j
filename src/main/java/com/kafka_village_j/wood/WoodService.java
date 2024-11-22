package com.kafka_village_j.wood;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka_village_j.config.exception.FailedRequestException;
import com.kafka_village_j.config.exception.enumeration.ExceptionCode;
import com.kafka_village_j.kafka.KafkaProducerService;
import com.kafka_village_j.wood.WoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WoodService {
    private final KafkaProducerService kafkaProducerService;
    private final WoodRepository woodRepository;

    private final String CREATE_TOPIC = "CREATE";
    private final String UPDATE_TOPIC = "UPDATE";
    private final String DELETE_TOPIC = "DELETE";

    public Boolean create(JsonNode message) {
        return kafkaProducerService.sendMessage(CREATE_TOPIC, message.toString());
    }

    public List<Log> read(String uuid) {
        return woodRepository.findAllByUuid(uuid).orElseThrow();
    }

    public Boolean update(JsonNode message) {
        return kafkaProducerService.sendMessage(UPDATE_TOPIC, message.toString());
    }

    public Boolean delete(JsonNode message) {
        return kafkaProducerService.sendMessage(DELETE_TOPIC, message.toString());
    }
}
package com.kafka_village_j.wood;

import com.fasterxml.jackson.databind.JsonNode;
import com.kafka_village_j.kafka.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LogService {
    private final KafkaProducerService kafkaProducerService;
    private final LogRepository logRepository;

    private final String CREATE_TOPIC = "CREATE_POSTGRES";
    private final String UPDATE_TOPIC = "UPDATE_POSTGRES";
    private final String DELETE_TOPIC = "DELETE_POSTGRES";

    public Boolean create(JsonNode message) {
        return kafkaProducerService.sendMessage(CREATE_TOPIC, message.toString());
    }

    public List<Log2> read(String uuid) {
        return (Objects.isNull(uuid) || uuid.isEmpty() ? logRepository.findAll() : logRepository.findAllByUuid(uuid).orElseThrow());
    }

    public Boolean update(JsonNode message) {
        return kafkaProducerService.sendMessage(UPDATE_TOPIC, message.toString());
    }

    public Boolean delete(JsonNode message) {
        return kafkaProducerService.sendMessage(DELETE_TOPIC, message.toString());
    }
}
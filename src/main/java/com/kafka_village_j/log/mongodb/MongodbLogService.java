package com.kafka_village_j.log.mongodb;

import com.fasterxml.jackson.databind.JsonNode;
import com.kafka_village_j.kafka.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MongodbLogService {
    private final KafkaProducer kafkaProducer;
    private final MongodbLogRepository woodRepository;
    private final String CREATE_TOPIC = "CREATE_MONGODB";
    private final String UPDATE_TOPIC = "UPDATE_MONGODB";
    private final String DELETE_TOPIC = "DELETE_MONGODB";

    public Boolean create(JsonNode message) {
        return kafkaProducer.sendMessage(CREATE_TOPIC, message.toString());
    }

    public List<Log> read(String uuid) {
        return woodRepository.findAllByUuid(uuid).orElseThrow();
    }

    public Boolean update(JsonNode message) {
        return kafkaProducer.sendMessage(UPDATE_TOPIC, message.toString());
    }

    public Boolean delete(JsonNode message) {
        return kafkaProducer.sendMessage(DELETE_TOPIC, message.toString());
    }
}
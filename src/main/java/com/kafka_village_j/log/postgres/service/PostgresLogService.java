package com.kafka_village_j.log.postgres.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.kafka_village_j.kafka.producer.KafkaProducer;
import com.kafka_village_j.log.postgres.entity.Log;
import com.kafka_village_j.log.postgres.repository.PostgresLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PostgresLogService {
    private final KafkaProducer kafkaProducer;
    private final PostgresLogRepository postgresLogRepository;

    private final String CREATE_TOPIC = "CREATE_POSTGRES";
    private final String UPDATE_TOPIC = "UPDATE_POSTGRES";
    private final String DELETE_TOPIC = "DELETE_POSTGRES";

    public Boolean create(JsonNode message) {
        return kafkaProducer.sendMessage(CREATE_TOPIC, message.toString());
    }

    public List<Log> read(String uuid) {
        return (Objects.isNull(uuid) || uuid.isEmpty() ? postgresLogRepository.findAll() : postgresLogRepository.findAllByUuid(uuid).orElseThrow());
    }

    public Boolean update(JsonNode message) {
        return kafkaProducer.sendMessage(UPDATE_TOPIC, message.toString());
    }

    public Boolean delete(JsonNode message) {
        return kafkaProducer.sendMessage(DELETE_TOPIC, message.toString());
    }
}
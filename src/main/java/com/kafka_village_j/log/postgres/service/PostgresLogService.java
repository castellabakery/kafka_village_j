package com.kafka_village_j.log.postgres.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kafka_village_j.kafka.enumeration.DdlType;
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

    private final String TOPIC = "POSTGRES";

    public Boolean create(JsonNode message) {
        return kafkaProducer.sendMessage(TOPIC, this.setType(message, DdlType.CREATE).toString());
    }

    public List<Log> read(String uuid) {
        return (Objects.isNull(uuid) || uuid.isEmpty() ? postgresLogRepository.findAll() : postgresLogRepository.findAllByUuid(uuid).orElseThrow());
    }

    public Boolean update(JsonNode message) {
        return kafkaProducer.sendMessage(TOPIC, this.setType(message, DdlType.UPDATE).toString());
    }

    public Boolean delete(JsonNode message) {
        return kafkaProducer.sendMessage(TOPIC, this.setType(message, DdlType.DELETE).toString());
    }

    private ObjectNode setType(JsonNode message, DdlType ddlType) {
        ObjectNode msg = new ObjectNode(JsonNodeFactory.instance);
        msg.put("type", ddlType.name());
        msg.put("message", message);
        return msg;
    }
}
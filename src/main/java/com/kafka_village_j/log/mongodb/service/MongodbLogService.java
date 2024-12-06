package com.kafka_village_j.log.mongodb.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.kafka_village_j.kafka.enumeration.DdlType;
import com.kafka_village_j.kafka.producer.KafkaProducer;
import com.kafka_village_j.log.mongodb.entity.Log;
import com.kafka_village_j.log.mongodb.repository.MongodbLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MongodbLogService {
    private final KafkaProducer kafkaProducer;
    private final MongodbLogRepository woodRepository;

    private final String TOPIC = "MONGODB";

    public Boolean create(JsonNode message) {
        return kafkaProducer.sendMessage(TOPIC, this.setType(message, DdlType.CREATE).toString());
    }

    public List<Log> read(String uuid) {
        return woodRepository.findAllByUuid(uuid).orElseThrow();
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
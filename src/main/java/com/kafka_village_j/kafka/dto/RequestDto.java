package com.kafka_village_j.kafka.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestDto {
    private final String topic;
    private final JsonNode message;

    @Builder
    public RequestDto(String topic, JsonNode message) {
        this.topic = topic;
        this.message = message;
    }
}

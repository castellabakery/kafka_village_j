package com.kafka_village_j.config.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestDto {
    private final String topic;
    private final String message;

    @Builder
    public RequestDto(String topic, String message) {
        this.topic = topic;
        this.message = message;
    }
}

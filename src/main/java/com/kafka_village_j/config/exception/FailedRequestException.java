package com.kafka_village_j.config.exception;

import lombok.Getter;

@Getter
public class FailedRequestException extends RuntimeException {

    private String code;
    private String reason;
    private String data;

    public FailedRequestException(String code, String reason, Object data) {
        super(reason + "=" + data);
        this.code = code;
        this.reason = reason;
        this.data = String.valueOf(data);
    }

    public FailedRequestException(String code, String reason) {
        super(reason);
        this.code = code;
        this.reason = reason;
    }

}
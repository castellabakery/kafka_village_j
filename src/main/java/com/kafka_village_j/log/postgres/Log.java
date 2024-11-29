package com.kafka_village_j.log.postgres;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "log")
public class Log {
    @Id
    private String uuid;
    private String name;
    private int age;
}

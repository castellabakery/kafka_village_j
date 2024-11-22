package com.kafka_village_j.wood;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "log")
@Getter
@Setter
@RequiredArgsConstructor
public class Log {
    @Id
    private String id;
    private String uuid;
    private String name;
    private int age;
}

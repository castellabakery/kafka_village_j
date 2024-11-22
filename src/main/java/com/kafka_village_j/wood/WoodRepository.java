package com.kafka_village_j.wood;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface WoodRepository extends MongoRepository<Log, String> {
    Optional<Log> findByUuid(String uuid);
}

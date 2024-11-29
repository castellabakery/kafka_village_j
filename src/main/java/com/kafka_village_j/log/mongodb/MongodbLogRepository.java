package com.kafka_village_j.log.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MongodbLogRepository extends MongoRepository<Log, String> {
    Optional<List<Log>> findAllByUuid(String uuid);
}

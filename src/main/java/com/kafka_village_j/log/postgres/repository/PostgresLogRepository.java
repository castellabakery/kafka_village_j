package com.kafka_village_j.log.postgres.repository;

import com.kafka_village_j.log.postgres.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostgresLogRepository extends JpaRepository<Log, String> {
    Optional<List<Log>> findAllByUuid(String uuid);
}

package com.kafka_village_j.wood;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LogRepository extends JpaRepository<Log2, String> {
    Optional<List<Log2>> findAllByUuid(String uuid);
}

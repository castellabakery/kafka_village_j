package com.kafka_village_j.log.mongodb.controller;

import com.kafka_village_j.kafka.dto.RequestDto;
import com.kafka_village_j.global.domain.dto.ResponseDto;
import com.kafka_village_j.log.mongodb.service.MongodbLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/log/mongodb")
public class MongodbLogController {
    private final MongodbLogService mongodbLogService;

    @PostMapping
    public ResponseEntity<ResponseDto<?>> create(@RequestBody RequestDto requestDto) {
        return ResponseEntity.ok(ResponseDto.success(mongodbLogService.create(requestDto.getMessage())));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<?>> read(@RequestParam String uuid) {
        return ResponseEntity.ok(ResponseDto.success(mongodbLogService.read(uuid)));
    }

    @PutMapping
    public ResponseEntity<ResponseDto<?>> update(@RequestBody RequestDto requestDto) {
        return ResponseEntity.ok(ResponseDto.success(mongodbLogService.update(requestDto.getMessage())));
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto<?>> delete(@RequestBody RequestDto requestDto) {
        return ResponseEntity.ok(ResponseDto.success(mongodbLogService.delete(requestDto.getMessage())));
    }
}

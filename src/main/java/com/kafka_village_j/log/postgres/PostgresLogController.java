package com.kafka_village_j.log.postgres;

import com.kafka_village_j.global.domain.dto.RequestDto;
import com.kafka_village_j.global.domain.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/log/postgres")
public class PostgresLogController {
    private final PostgresLogService postgresLogService;

    @PostMapping
    public ResponseEntity<ResponseDto<?>> create(@RequestBody RequestDto requestDto) {
        return ResponseEntity.ok(ResponseDto.success(postgresLogService.create(requestDto.getMessage())));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<?>> read(@RequestParam(required = false) String uuid) {
        return ResponseEntity.ok(ResponseDto.success(postgresLogService.read(uuid)));
    }

    @PutMapping
    public ResponseEntity<ResponseDto<?>> update(@RequestBody RequestDto requestDto) {
        return ResponseEntity.ok(ResponseDto.success(postgresLogService.update(requestDto.getMessage())));
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto<?>> delete(@RequestBody RequestDto requestDto) {
        return ResponseEntity.ok(ResponseDto.success(postgresLogService.delete(requestDto.getMessage())));
    }
}

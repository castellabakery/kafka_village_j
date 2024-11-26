package com.kafka_village_j.wood;

import com.kafka_village_j.config.domain.dto.RequestDto;
import com.kafka_village_j.config.domain.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/log")
public class PostgresLogController {
    private final LogService logService;

    @PostMapping
    public ResponseEntity<ResponseDto<?>> create(@RequestBody RequestDto requestDto) {
        return ResponseEntity.ok(ResponseDto.success(logService.create(requestDto.getMessage())));
    }

//    @GetMapping
//    public ResponseEntity<ResponseDto<?>> read(@RequestParam String uuid) {
//        return ResponseEntity.ok(ResponseDto.success(logService.read(uuid)));
//    }

    @PutMapping
    public ResponseEntity<ResponseDto<?>> update(@RequestBody RequestDto requestDto) {
        return ResponseEntity.ok(ResponseDto.success(logService.update(requestDto.getMessage())));
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto<?>> delete(@RequestBody RequestDto requestDto) {
        return ResponseEntity.ok(ResponseDto.success(logService.delete(requestDto.getMessage())));
    }
}

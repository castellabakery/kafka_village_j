package com.kafka_village_j.wood;

import com.kafka_village_j.config.domain.dto.RequestDto;
import com.kafka_village_j.config.domain.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wood")
public class WoodController {
    private final WoodService woodService;

    @PostMapping
    public ResponseEntity<ResponseDto<?>> create(@RequestBody RequestDto requestDto) {
        return ResponseEntity.ok(ResponseDto.success(woodService.create(requestDto.getMessage())));
    }

    @GetMapping
    public ResponseEntity<ResponseDto<?>> read(@RequestParam String uuid) {
        return ResponseEntity.ok(ResponseDto.success(woodService.read(uuid)));
    }

    @PutMapping
    public ResponseEntity<ResponseDto<?>> update(@RequestBody RequestDto requestDto) {
        return ResponseEntity.ok(ResponseDto.success(woodService.update(requestDto.getMessage())));
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto<?>> delete(@RequestBody RequestDto requestDto) {
        return ResponseEntity.ok(ResponseDto.success(woodService.delete(requestDto.getMessage())));
    }
}

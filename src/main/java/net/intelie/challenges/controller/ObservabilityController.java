package net.intelie.challenges.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ObservabilityController {

    @GetMapping("Test")
    public ResponseEntity<String> test() {
        return ResponseEntity.of(Optional.of("API EventStore - Health Check"));
    }
}

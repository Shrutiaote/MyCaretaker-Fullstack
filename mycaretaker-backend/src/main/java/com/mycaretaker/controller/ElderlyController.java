package com.mycaretaker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mycaretaker.entity.Elderly;
import com.mycaretaker.service.ElderlyService;

@RestController
@RequestMapping("/api/elderly")
@CrossOrigin(origins = "http://localhost:5173")
public class ElderlyController {

    private final ElderlyService elderlyService;

    public ElderlyController(ElderlyService elderlyService) {
        this.elderlyService = elderlyService;
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<?> createElderlyProfile(
            @PathVariable int userId,
            @RequestBody Elderly elderly) {

        try {

            Elderly savedElderly =
                    elderlyService.createElderlyProfile(
                            userId,
                            elderly);

            return ResponseEntity.ok(savedElderly);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getElderlyByUser(
            @PathVariable int userId) {

        Elderly elderly =
                elderlyService.getElderlyByUserId(userId);

        if (elderly == null) {

            return ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity.ok(elderly);
    }
    
    @PutMapping("/user/{userId}")
    public ResponseEntity<?> updateElderlyProfile(
            @PathVariable int userId,
            @RequestBody Elderly elderly) {

        try {

            Elderly updatedElderly =
                    elderlyService.updateElderlyProfile(
                            userId,
                            elderly);

            return ResponseEntity.ok(updatedElderly);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
    
    @PostMapping("/user/{userId}/connection-code")
    public ResponseEntity<String> generateConnectionCode(
            @PathVariable int userId) {

        String code =
                elderlyService.generateConnectionCode(userId);

        return ResponseEntity.ok(code);
    }
    
    @GetMapping("/connection/{connectionCode}")
    public ResponseEntity<Elderly> getByConnectionCode(
            @PathVariable String connectionCode) {

        return ResponseEntity.ok(
                elderlyService.getByConnectionCode(
                        connectionCode));
    }
}
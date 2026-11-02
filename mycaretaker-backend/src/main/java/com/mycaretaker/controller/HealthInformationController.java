package com.mycaretaker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mycaretaker.entity.HealthInformation;
import com.mycaretaker.service.HealthInformationService;

@RestController
@RequestMapping("/api/health-information")
@CrossOrigin(origins = "http://localhost:5173")
public class HealthInformationController {

    private final HealthInformationService healthInformationService;

    public HealthInformationController(
            HealthInformationService healthInformationService) {

        this.healthInformationService = healthInformationService;
    }

    @PostMapping("/elderly/{elderlyId}")
    public ResponseEntity<?> saveHealthInformation(
            @PathVariable int elderlyId,
            @RequestBody HealthInformation healthInformation) {

        try {

            HealthInformation savedHealthInformation =
                    healthInformationService.saveHealthInformation(
                            elderlyId,
                            healthInformation);

            return ResponseEntity.ok(savedHealthInformation);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/elderly/{elderlyId}")
    public ResponseEntity<?> getHealthInformation(
            @PathVariable int elderlyId) {

        HealthInformation healthInformation =
                healthInformationService
                        .getHealthInformation(elderlyId);

        if (healthInformation == null) {

            return ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity.ok(healthInformation);
    }
}
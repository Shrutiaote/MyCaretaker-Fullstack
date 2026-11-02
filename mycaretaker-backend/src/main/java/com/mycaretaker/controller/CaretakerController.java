package com.mycaretaker.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mycaretaker.entity.Caretaker;
import com.mycaretaker.service.CaretakerService;

@RestController
@RequestMapping("/api/caretakers")
@CrossOrigin(origins = "http://localhost:5173")
public class CaretakerController {

    private final CaretakerService caretakerService;

    public CaretakerController(
            CaretakerService caretakerService) {

        this.caretakerService = caretakerService;
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<?> createCaretakerProfile(
            @PathVariable int userId,
            @RequestBody Caretaker caretaker) {

        try {

            Caretaker savedCaretaker =
                    caretakerService.createCaretakerProfile(
                            userId,
                            caretaker);

            return ResponseEntity.ok(savedCaretaker);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getCaretakerByUser(
            @PathVariable int userId) {

        Caretaker caretaker =
                caretakerService.getCaretakerByUserId(userId);

        if (caretaker == null) {

            return ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity.ok(caretaker);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<?> updateCaretakerProfile(
            @PathVariable int userId,
            @RequestBody Caretaker caretaker) {

        try {

            Caretaker updatedCaretaker =
                    caretakerService.updateCaretakerProfile(
                            userId,
                            caretaker);

            return ResponseEntity.ok(updatedCaretaker);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/verified")
    public ResponseEntity<List<Caretaker>>
            getVerifiedCaretakers() {

        return ResponseEntity.ok(
                caretakerService.getVerifiedCaretakers()
        );
    }
    
    @GetMapping
    public ResponseEntity<List<Caretaker>> getAllCaretakers() {

        return ResponseEntity.ok(
                caretakerService.getAllCaretakers()
        );
    }
}
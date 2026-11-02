package com.mycaretaker.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mycaretaker.entity.Caretaker;
import com.mycaretaker.service.CaretakerService;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    private final CaretakerService caretakerService;

    public AdminController(
            CaretakerService caretakerService) {

        this.caretakerService = caretakerService;
    }

    @PutMapping("/caretakers/{caretakerId}/verify")
    public ResponseEntity<?> verifyCaretaker(
            @PathVariable int caretakerId) {

        try {

            Caretaker verifiedCaretaker =
                    caretakerService.verifyCaretaker(
                            caretakerId);

            return ResponseEntity.ok(
                    verifiedCaretaker
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}
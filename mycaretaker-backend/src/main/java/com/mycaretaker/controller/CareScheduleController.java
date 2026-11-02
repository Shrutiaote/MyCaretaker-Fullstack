package com.mycaretaker.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mycaretaker.entity.CareSchedule;
import com.mycaretaker.service.CareScheduleService;

@RestController
@RequestMapping("/api/care-schedules")
@CrossOrigin(origins = "http://localhost:5173")
public class CareScheduleController {

    private final CareScheduleService careScheduleService;

    public CareScheduleController(
            CareScheduleService careScheduleService) {

        this.careScheduleService = careScheduleService;
    }

    @PostMapping(
        "/elderly/{elderlyId}/caretaker/{caretakerId}"
    )
    public ResponseEntity<?> createSchedule(
            @PathVariable int elderlyId,
            @PathVariable int caretakerId,
            @RequestBody CareSchedule schedule) {

        try {

            CareSchedule savedSchedule =
                    careScheduleService.createSchedule(
                            elderlyId,
                            caretakerId,
                            schedule);

            return ResponseEntity.ok(savedSchedule);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping("/elderly/{elderlyId}")
    public ResponseEntity<List<CareSchedule>>
            getSchedulesForElderly(
                    @PathVariable int elderlyId) {

        return ResponseEntity.ok(
                careScheduleService
                        .getSchedulesForElderly(elderlyId)
        );
    }

    @GetMapping("/caretaker/{caretakerId}")
    public ResponseEntity<List<CareSchedule>>
            getSchedulesForCaretaker(
                    @PathVariable int caretakerId) {

        return ResponseEntity.ok(
                careScheduleService
                        .getSchedulesForCaretaker(caretakerId)
        );
    }

    @PutMapping("/{scheduleId}/status")
    public ResponseEntity<?> updateScheduleStatus(
            @PathVariable int scheduleId,
            @RequestParam String status) {

        try {

            CareSchedule updatedSchedule =
                    careScheduleService.updateScheduleStatus(
                            scheduleId,
                            status);

            return ResponseEntity.ok(updatedSchedule);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}
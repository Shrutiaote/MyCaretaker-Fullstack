
package com.mycaretaker.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mycaretaker.entity.CareRequest;
import com.mycaretaker.service.CareRequestService;

@RestController
@RequestMapping("/api/care-requests")
@CrossOrigin(origins = "http://localhost:5173")
public class CareRequestController {

    private final CareRequestService careRequestService;

    public CareRequestController(
            CareRequestService careRequestService) {

        this.careRequestService = careRequestService;
    }


    // =========================================================
    // ELDERLY -> CARETAKER
    // =========================================================

    @PostMapping(
        "/elderly/{elderlyId}/caretaker/{caretakerId}"
    )
    public ResponseEntity<?> createCareRequest(
            @PathVariable int elderlyId,
            @PathVariable int caretakerId,
            @RequestBody String message) {

        try {

            CareRequest careRequest =
                    careRequestService.createCareRequest(
                            elderlyId,
                            caretakerId,
                            message);

            return ResponseEntity.ok(careRequest);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }


    // =========================================================
    // GET REQUESTS FOR ELDERLY
    // =========================================================

    @GetMapping("/elderly/{elderlyId}")
    public ResponseEntity<List<CareRequest>>
            getRequestsForElderly(
                    @PathVariable int elderlyId) {

        return ResponseEntity.ok(
                careRequestService
                        .getCareRequestsForElderly(
                                elderlyId)
        );
    }


    // =========================================================
    // GET REQUESTS FOR CARETAKER
    // =========================================================

    @GetMapping("/caretaker/{caretakerId}")
    public ResponseEntity<List<CareRequest>>
            getRequestsForCaretaker(
                    @PathVariable int caretakerId) {

        return ResponseEntity.ok(
                careRequestService
                        .getCareRequestsForCaretaker(
                                caretakerId)
        );
    }


    // =========================================================
    // UPDATE CARE REQUEST STATUS
    // =========================================================

    @PutMapping("/{careRequestId}/status")
    public ResponseEntity<?> updateRequestStatus(
            @PathVariable int careRequestId,
            @RequestParam String status) {

        try {

            CareRequest updatedRequest =
                    careRequestService
                            .updateCareRequestStatus(
                                    careRequestId,
                                    status);

            return ResponseEntity.ok(updatedRequest);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }


    // =========================================================
    // GET CARE REQUEST BY ID
    // =========================================================

    @GetMapping("/{careRequestId}")
    public ResponseEntity<?> getCareRequestById(
            @PathVariable int careRequestId) {

        try {

            CareRequest careRequest =
                    careRequestService
                            .getCareRequestById(
                                    careRequestId);

            return ResponseEntity.ok(careRequest);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }


    // =========================================================
    // FAMILY MEMBER -> CARETAKER
    // =========================================================

    @PostMapping(
        "/family-member/{familyMemberId}/caretaker/{caretakerId}"
    )
    public ResponseEntity<CareRequest>
            createFamilyMemberCareRequest(
                    @PathVariable int familyMemberId,
                    @PathVariable int caretakerId,
                    @RequestBody String message) {

        return ResponseEntity.ok(
                careRequestService
                        .createFamilyMemberCareRequest(
                                familyMemberId,
                                caretakerId,
                                message)
        );
    }


    // =========================================================
    // GET REQUESTS FOR FAMILY MEMBER
    // =========================================================

    @GetMapping(
        "/family-member/{familyMemberId}"
    )
    public ResponseEntity<List<CareRequest>>
            getCareRequestsForFamilyMember(
                    @PathVariable int familyMemberId) {

        return ResponseEntity.ok(
                careRequestService
                        .getCareRequestsForFamilyMember(
                                familyMemberId)
        );
    }
}

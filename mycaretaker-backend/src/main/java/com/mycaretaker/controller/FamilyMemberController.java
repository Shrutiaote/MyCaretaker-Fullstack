package com.mycaretaker.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mycaretaker.entity.Elderly;
import com.mycaretaker.entity.FamilyMember;
import com.mycaretaker.service.FamilyMemberService;

@RestController
@RequestMapping("/api/family-members")
@CrossOrigin(origins = "http://localhost:5173")
public class FamilyMemberController {

    private final FamilyMemberService familyMemberService;

    public FamilyMemberController(
            FamilyMemberService familyMemberService) {

        this.familyMemberService = familyMemberService;
    }

    // Create Family Member Profile
    @PostMapping("/user/{userId}")
    public ResponseEntity<FamilyMember> createProfile(
            @PathVariable int userId,
            @RequestBody FamilyMember familyMember) {

        return ResponseEntity.ok(
                familyMemberService.createProfile(
                        userId,
                        familyMember));
    }

    // Get Family Member Profile
    @GetMapping("/user/{userId}")
    public ResponseEntity<FamilyMember> getProfile(
            @PathVariable int userId) {

        return ResponseEntity.ok(
                familyMemberService.getProfile(userId));
    }

    // Update Family Member Profile
    @PutMapping("/user/{userId}")
    public ResponseEntity<FamilyMember> updateProfile(
            @PathVariable int userId,
            @RequestBody FamilyMember familyMember) {

        return ResponseEntity.ok(
                familyMemberService.updateProfile(
                        userId,
                        familyMember));
    }

    // Connect Family Member to Elderly Person
    @PutMapping("/user/{userId}/elderly/{elderlyId}")
    public ResponseEntity<FamilyMember> connectToElderly(
            @PathVariable int userId,
            @PathVariable int elderlyId) {

        return ResponseEntity.ok(
                familyMemberService.connectToElderly(
                        userId,
                        elderlyId));
    }

    // Get Connected Elderly Person
    @GetMapping("/user/{userId}/elderly")
    public ResponseEntity<Elderly> getConnectedElderly(
            @PathVariable int userId) {

        return ResponseEntity.ok(
                familyMemberService.getConnectedElderly(
                        userId));
    }

    // Get All Family Members of an Elderly Person
    @GetMapping("/elderly/{elderlyId}")
    public ResponseEntity<List<FamilyMember>> getFamilyMembers(
            @PathVariable int elderlyId) {

        return ResponseEntity.ok(
                familyMemberService.getFamilyMembers(
                        elderlyId));
    }
}
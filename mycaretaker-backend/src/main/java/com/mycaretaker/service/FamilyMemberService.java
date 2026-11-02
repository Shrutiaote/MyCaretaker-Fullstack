package com.mycaretaker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mycaretaker.entity.Elderly;
import com.mycaretaker.entity.FamilyMember;
import com.mycaretaker.entity.User;
import com.mycaretaker.repository.ElderlyRepository;
import com.mycaretaker.repository.FamilyMemberRepository;
import com.mycaretaker.repository.UserRepository;

@Service
public class FamilyMemberService {

    private final FamilyMemberRepository familyMemberRepository;
    private final UserRepository userRepository;
    private final ElderlyRepository elderlyRepository;

    public FamilyMemberService(
            FamilyMemberRepository familyMemberRepository,
            UserRepository userRepository,
            ElderlyRepository elderlyRepository) {

        this.familyMemberRepository = familyMemberRepository;
        this.userRepository = userRepository;
        this.elderlyRepository = elderlyRepository;
    }

    // Create Family Member Profile
    public FamilyMember createProfile(
            int userId,
            FamilyMember familyMember) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                    new RuntimeException("User not found"));

        familyMember.setUser(user);

        return familyMemberRepository.save(familyMember);
    }

    // Get Family Member Profile
    public FamilyMember getProfile(int userId) {

        return familyMemberRepository
                .findByUserUserId(userId)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Family member profile not found"));
    }

    // Update Family Member Profile
    public FamilyMember updateProfile(
            int userId,
            FamilyMember updatedFamilyMember) {

        FamilyMember existingFamilyMember =
                familyMemberRepository
                    .findByUserUserId(userId)
                    .orElseThrow(() ->
                        new RuntimeException(
                            "Family member profile not found"));

        existingFamilyMember.setRelationshipWithElderly(
                updatedFamilyMember
                    .getRelationshipWithElderly());

        existingFamilyMember.setEmergencyContact(
                updatedFamilyMember
                    .getEmergencyContact());

        return familyMemberRepository
                .save(existingFamilyMember);
    }

    // Connect Family Member with Elderly
    public FamilyMember connectToElderly(
            int userId,
            int elderlyId) {

        FamilyMember familyMember =
                familyMemberRepository
                    .findByUserUserId(userId)
                    .orElseThrow(() ->
                        new RuntimeException(
                            "Family member profile not found"));

        Elderly elderly =
                elderlyRepository.findById(elderlyId)
                    .orElseThrow(() ->
                        new RuntimeException(
                            "Elderly person not found"));

        familyMember.setElderly(elderly);

        return familyMemberRepository
                .save(familyMember);
    }

    // Get Connected Elderly Person
    public Elderly getConnectedElderly(int userId) {

        FamilyMember familyMember =
                familyMemberRepository
                    .findByUserUserId(userId)
                    .orElseThrow(() ->
                        new RuntimeException(
                            "Family member profile not found"));

        if (familyMember.getElderly() == null) {
            throw new RuntimeException(
                    "No elderly person connected");
        }

        return familyMember.getElderly();
    }

    // Get Family Members of an Elderly Person
    public List<FamilyMember> getFamilyMembers(
            int elderlyId) {

        return familyMemberRepository
                .findByElderlyElderlyId(elderlyId);
    }
}
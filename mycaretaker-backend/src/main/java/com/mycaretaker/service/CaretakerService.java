package com.mycaretaker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mycaretaker.entity.Caretaker;
import com.mycaretaker.entity.User;
import com.mycaretaker.repository.CaretakerRepository;
import com.mycaretaker.repository.UserRepository;

@Service
public class CaretakerService {

    private final CaretakerRepository caretakerRepository;
    private final UserRepository userRepository;

    public CaretakerService(
            CaretakerRepository caretakerRepository,
            UserRepository userRepository) {

        this.caretakerRepository = caretakerRepository;
        this.userRepository = userRepository;
    }

    public Caretaker createCaretakerProfile(
            int userId,
            Caretaker caretaker) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (caretakerRepository
                .findByUserUserId(userId)
                .isPresent()) {

            throw new RuntimeException(
                    "Caretaker profile already exists");
        }

        caretaker.setUser(user);

        if (caretaker.getVerificationStatus() == null ||
                caretaker.getVerificationStatus().isBlank()) {

            caretaker.setVerificationStatus("PENDING");
        }

        return caretakerRepository.save(caretaker);
    }

    public Caretaker getCaretakerByUserId(int userId) {

        return caretakerRepository
                .findByUserUserId(userId)
                .orElse(null);
    }

    public List<Caretaker> getVerifiedCaretakers() {

        return caretakerRepository
                .findByVerificationStatus("VERIFIED");
    }

    public Caretaker updateCaretakerProfile(
            int userId,
            Caretaker updatedCaretaker) {

        Caretaker existingCaretaker =
                caretakerRepository
                        .findByUserUserId(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Caretaker profile not found"));

        existingCaretaker.setExperience(
                updatedCaretaker.getExperience());

        existingCaretaker.setQualification(
                updatedCaretaker.getQualification());

        existingCaretaker.setSkills(
                updatedCaretaker.getSkills());

        existingCaretaker.setAvailability(
                updatedCaretaker.getAvailability());

        existingCaretaker.setHourlyRate(
                updatedCaretaker.getHourlyRate());

        existingCaretaker.setAbout(
                updatedCaretaker.getAbout());

        return caretakerRepository.save(
                existingCaretaker);
    }
    
    public Caretaker verifyCaretaker(int caretakerId) {

        Caretaker caretaker =
                caretakerRepository.findById(caretakerId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Caretaker not found"));

        caretaker.setVerificationStatus("VERIFIED");

        return caretakerRepository.save(caretaker);
    }
    
    public List<Caretaker> getAllCaretakers() {

        return caretakerRepository.findAll();
    }
}
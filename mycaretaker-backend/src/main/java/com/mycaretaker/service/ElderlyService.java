package com.mycaretaker.service;

import org.springframework.stereotype.Service;

import com.mycaretaker.entity.Elderly;
import com.mycaretaker.entity.User;
import com.mycaretaker.repository.ElderlyRepository;
import com.mycaretaker.repository.UserRepository;
import java.util.UUID;

@Service
public class ElderlyService {

    private final ElderlyRepository elderlyRepository;
    private final UserRepository userRepository;

    public ElderlyService(
            ElderlyRepository elderlyRepository,
            UserRepository userRepository) {

        this.elderlyRepository = elderlyRepository;
        this.userRepository = userRepository;
    }

    public Elderly createElderlyProfile(
            int userId,
            Elderly elderly) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        if (elderlyRepository.findByUserUserId(userId).isPresent()) {
            throw new RuntimeException(
                    "Elderly profile already exists");
        }

        elderly.setUser(user);

        return elderlyRepository.save(elderly);
    }

    public Elderly getElderlyByUserId(int userId) {

        return elderlyRepository
                .findByUserUserId(userId)
                .orElse(null);
    }
    
    public Elderly updateElderlyProfile(
            int userId,
            Elderly updatedElderly) {

        Elderly existingElderly =
                elderlyRepository.findByUserUserId(userId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Elderly profile not found"));

        existingElderly.setGender(
                updatedElderly.getGender());

        existingElderly.setCareRequirements(
                updatedElderly.getCareRequirements());

        existingElderly.setEmergencyContactName(
                updatedElderly.getEmergencyContactName());

        existingElderly.setEmergencyContactRelationship(
                updatedElderly.getEmergencyContactRelationship());

        existingElderly.setEmergencyContactPhone(
                updatedElderly.getEmergencyContactPhone());

        existingElderly.setPreferredCareType(
                updatedElderly.getPreferredCareType());

        existingElderly.setAdditionalInformation(
                updatedElderly.getAdditionalInformation());

        return elderlyRepository.save(existingElderly);
    }
    
    public String generateConnectionCode(int userId) {

        Elderly elderly = elderlyRepository
                .findByUserUserId(userId)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Elderly profile not found"));

        String code;

        do {
            code = "MC-" +
                   UUID.randomUUID()
                       .toString()
                       .substring(0, 8)
                       .toUpperCase();

        } while (elderlyRepository
                .findByConnectionCode(code)
                .isPresent());

        elderly.setConnectionCode(code);

        elderlyRepository.save(elderly);

        return code;
    }
    
    public Elderly getByConnectionCode(String connectionCode) {

        return elderlyRepository
                .findByConnectionCode(connectionCode)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Invalid connection code"));
    }
}
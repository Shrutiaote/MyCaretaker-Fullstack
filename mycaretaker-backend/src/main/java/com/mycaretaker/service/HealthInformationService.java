package com.mycaretaker.service;

import org.springframework.stereotype.Service;

import com.mycaretaker.entity.Elderly;
import com.mycaretaker.entity.HealthInformation;
import com.mycaretaker.repository.ElderlyRepository;
import com.mycaretaker.repository.HealthInformationRepository;

@Service
public class HealthInformationService {

    private final HealthInformationRepository healthInformationRepository;
    private final ElderlyRepository elderlyRepository;

    public HealthInformationService(
            HealthInformationRepository healthInformationRepository,
            ElderlyRepository elderlyRepository) {

        this.healthInformationRepository = healthInformationRepository;
        this.elderlyRepository = elderlyRepository;
    }


    public HealthInformation saveHealthInformation(
            int elderlyId,
            HealthInformation healthInformation) {

        Elderly elderly = elderlyRepository.findById(elderlyId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Elderly person not found"));

        HealthInformation existingHealthInformation =
                healthInformationRepository
                        .findByElderlyElderlyId(elderlyId)
                        .orElse(null);

        if (existingHealthInformation != null) {

            existingHealthInformation.setBloodGroup(
                    healthInformation.getBloodGroup());

            existingHealthInformation.setMedicalConditions(
                    healthInformation.getMedicalConditions());

            existingHealthInformation.setAllergies(
                    healthInformation.getAllergies());

            existingHealthInformation.setCurrentMedications(
                    healthInformation.getCurrentMedications());

            existingHealthInformation.setPastSurgeries(
                    healthInformation.getPastSurgeries());

            existingHealthInformation.setMobilityStatus(
                    healthInformation.getMobilityStatus());

            existingHealthInformation.setDietaryRequirements(
                    healthInformation.getDietaryRequirements());

            existingHealthInformation.setDoctorName(
                    healthInformation.getDoctorName());

            existingHealthInformation.setDoctorContact(
                    healthInformation.getDoctorContact());

            existingHealthInformation
                    .setAdditionalHealthInformation(
                            healthInformation
                                    .getAdditionalHealthInformation());

            return healthInformationRepository
                    .save(existingHealthInformation);
        }

        healthInformation.setElderly(elderly);

        return healthInformationRepository
                .save(healthInformation);
    }


    public HealthInformation getHealthInformation(int elderlyId) {

        return healthInformationRepository
                .findByElderlyElderlyId(elderlyId)
                .orElse(null);
    }
}
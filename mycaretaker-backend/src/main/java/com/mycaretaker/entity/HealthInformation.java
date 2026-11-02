package com.mycaretaker.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "health_information")
public class HealthInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int healthInformationId;

    @OneToOne
    @JoinColumn(name = "elderly_id", nullable = false, unique = true)
    private Elderly elderly;

    @Column(length = 20)
    private String bloodGroup;

    @Column(length = 1000)
    private String medicalConditions;

    @Column(length = 1000)
    private String allergies;

    @Column(length = 1000)
    private String currentMedications;

    @Column(length = 1000)
    private String pastSurgeries;

    @Column(length = 100)
    private String mobilityStatus;

    @Column(length = 1000)
    private String dietaryRequirements;

    @Column(length = 200)
    private String doctorName;

    @Column(length = 20)
    private String doctorContact;

    @Column(length = 1500)
    private String additionalHealthInformation;


    public HealthInformation() {
    }


    public int getHealthInformationId() {
        return healthInformationId;
    }

    public void setHealthInformationId(int healthInformationId) {
        this.healthInformationId = healthInformationId;
    }

    public Elderly getElderly() {
        return elderly;
    }

    public void setElderly(Elderly elderly) {
        this.elderly = elderly;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getMedicalConditions() {
        return medicalConditions;
    }

    public void setMedicalConditions(String medicalConditions) {
        this.medicalConditions = medicalConditions;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getCurrentMedications() {
        return currentMedications;
    }

    public void setCurrentMedications(String currentMedications) {
        this.currentMedications = currentMedications;
    }

    public String getPastSurgeries() {
        return pastSurgeries;
    }

    public void setPastSurgeries(String pastSurgeries) {
        this.pastSurgeries = pastSurgeries;
    }

    public String getMobilityStatus() {
        return mobilityStatus;
    }

    public void setMobilityStatus(String mobilityStatus) {
        this.mobilityStatus = mobilityStatus;
    }

    public String getDietaryRequirements() {
        return dietaryRequirements;
    }

    public void setDietaryRequirements(String dietaryRequirements) {
        this.dietaryRequirements = dietaryRequirements;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorContact() {
        return doctorContact;
    }

    public void setDoctorContact(String doctorContact) {
        this.doctorContact = doctorContact;
    }

    public String getAdditionalHealthInformation() {
        return additionalHealthInformation;
    }

    public void setAdditionalHealthInformation(String additionalHealthInformation) {
        this.additionalHealthInformation = additionalHealthInformation;
    }
}
package com.mycaretaker.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "elderly")
public class Elderly {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int elderlyId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(length = 20)
    private String gender;

    @Column(length = 1000)
    private String careRequirements;
    
    @Column(name = "connection_code", unique = true)
    private String connectionCode;

    @Column(length = 100)
    private String emergencyContactName;

    @Column(length = 100)
    private String emergencyContactRelationship;

    @Column(length = 20)
    private String emergencyContactPhone;

    @Column(length = 200)
    private String preferredCareType;

    @Column(length = 1500)
    private String additionalInformation;

    public Elderly() {
    }

    public int getElderlyId() {
        return elderlyId;
    }

    public void setElderlyId(int elderlyId) {
        this.elderlyId = elderlyId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCareRequirements() {
        return careRequirements;
    }

    public void setCareRequirements(String careRequirements) {
        this.careRequirements = careRequirements;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactRelationship() {
        return emergencyContactRelationship;
    }

    public void setEmergencyContactRelationship(
            String emergencyContactRelationship) {

        this.emergencyContactRelationship =
                emergencyContactRelationship;
    }

    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }

    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public String getPreferredCareType() {
        return preferredCareType;
    }

    public void setPreferredCareType(String preferredCareType) {
        this.preferredCareType = preferredCareType;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(
            String additionalInformation) {

        this.additionalInformation = additionalInformation;
    }
    
    public String getConnectionCode() {
        return connectionCode;
    }

    public void setConnectionCode(String connectionCode) {
        this.connectionCode = connectionCode;
    }
}
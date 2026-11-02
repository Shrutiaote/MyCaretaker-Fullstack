package com.mycaretaker.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "caretakers")
public class Caretaker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int caretakerId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(length = 1000)
    private String experience;

    @Column(length = 500)
    private String qualification;

    @Column(length = 1000)
    private String skills;

    @Column(length = 100)
    private String availability;

    private double hourlyRate;

    @Column(length = 1500)
    private String about;

    @Column(length = 50)
    private String verificationStatus;

    public Caretaker() {
    }

    public int getCaretakerId() {
        return caretakerId;
    }

    public void setCaretakerId(int caretakerId) {
        this.caretakerId = caretakerId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }
}
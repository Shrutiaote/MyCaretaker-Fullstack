package com.mycaretaker.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "care_requests")
public class CareRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int careRequestId;

    @ManyToOne
    @JoinColumn(name = "elderly_id", nullable = false)
    private Elderly elderly;

    @ManyToOne
    @JoinColumn(name = "caretaker_id", nullable = false)
    private Caretaker caretaker;

    @Column(length = 1500)
    private String message;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDateTime requestDate;
    
    @ManyToOne
    @JoinColumn(name = "family_member_id")
    private FamilyMember familyMember;

    public CareRequest() {
    }

    public int getCareRequestId() {
        return careRequestId;
    }

    public void setCareRequestId(int careRequestId) {
        this.careRequestId = careRequestId;
    }

    public Elderly getElderly() {
        return elderly;
    }

    public void setElderly(Elderly elderly) {
        this.elderly = elderly;
    }

    public Caretaker getCaretaker() {
        return caretaker;
    }

    public void setCaretaker(Caretaker caretaker) {
        this.caretaker = caretaker;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }
    
    public FamilyMember getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(FamilyMember familyMember) {
        this.familyMember = familyMember;
    }
}
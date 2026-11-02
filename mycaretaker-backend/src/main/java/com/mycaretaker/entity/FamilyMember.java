package com.mycaretaker.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "family_members")
public class FamilyMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int familyMemberId;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "elderly_id")
    private Elderly elderly;

    @Column(name = "relationship_with_elderly")
    private String relationshipWithElderly;

    @Column(name = "emergency_contact")
    private String emergencyContact;

    public FamilyMember() {
    }

    public int getFamilyMemberId() {
        return familyMemberId;
    }

    public void setFamilyMemberId(int familyMemberId) {
        this.familyMemberId = familyMemberId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Elderly getElderly() {
        return elderly;
    }

    public void setElderly(Elderly elderly) {
        this.elderly = elderly;
    }

    public String getRelationshipWithElderly() {
        return relationshipWithElderly;
    }

    public void setRelationshipWithElderly(
            String relationshipWithElderly) {

        this.relationshipWithElderly =
                relationshipWithElderly;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact =
                emergencyContact;
    }
}
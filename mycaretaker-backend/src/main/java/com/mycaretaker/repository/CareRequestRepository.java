package com.mycaretaker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycaretaker.entity.CareRequest;

public interface CareRequestRepository
        extends JpaRepository<CareRequest, Integer> {

    List<CareRequest> findByElderlyElderlyId(int elderlyId);

    List<CareRequest> findByCaretakerCaretakerId(int caretakerId);

    List<CareRequest> findByCaretakerCaretakerIdAndStatus(
            int caretakerId,
            String status);
    
    List<CareRequest> findByFamilyMemberFamilyMemberId(
            int familyMemberId);
}
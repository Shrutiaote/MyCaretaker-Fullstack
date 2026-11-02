package com.mycaretaker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycaretaker.entity.FamilyMember;

public interface FamilyMemberRepository
        extends JpaRepository<FamilyMember, Integer> {

    Optional<FamilyMember> findByUserUserId(int userId);

    java.util.List<FamilyMember> findByElderlyElderlyId(int elderlyId);
}
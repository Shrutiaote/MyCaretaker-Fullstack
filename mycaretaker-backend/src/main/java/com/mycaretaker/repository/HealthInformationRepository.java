package com.mycaretaker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycaretaker.entity.HealthInformation;

public interface HealthInformationRepository
        extends JpaRepository<HealthInformation, Integer> {

    Optional<HealthInformation> findByElderlyElderlyId(int elderlyId);
}
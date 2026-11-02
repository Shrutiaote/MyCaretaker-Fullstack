package com.mycaretaker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycaretaker.entity.Elderly;

public interface ElderlyRepository extends JpaRepository<Elderly, Integer> {

    Optional<Elderly> findByUserUserId(int userId);
    
    Optional<Elderly> findByConnectionCode(String connectionCode);
}
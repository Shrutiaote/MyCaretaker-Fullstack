package com.mycaretaker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycaretaker.entity.Caretaker;

public interface CaretakerRepository
        extends JpaRepository<Caretaker, Integer> {

    Optional<Caretaker> findByUserUserId(int userId);

    List<Caretaker> findByVerificationStatus(String verificationStatus);
}
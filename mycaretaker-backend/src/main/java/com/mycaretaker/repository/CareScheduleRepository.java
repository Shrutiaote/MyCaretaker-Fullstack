package com.mycaretaker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycaretaker.entity.CareSchedule;

public interface CareScheduleRepository
        extends JpaRepository<CareSchedule, Integer> {

    List<CareSchedule> findByElderlyElderlyId(
            int elderlyId);

    List<CareSchedule> findByCaretakerCaretakerId(
            int caretakerId);

    List<CareSchedule> findByCaretakerCaretakerIdAndStatus(
            int caretakerId,
            String status);
}
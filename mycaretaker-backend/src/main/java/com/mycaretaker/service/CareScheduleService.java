package com.mycaretaker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mycaretaker.entity.CareSchedule;
import com.mycaretaker.entity.Caretaker;
import com.mycaretaker.entity.Elderly;
import com.mycaretaker.repository.CareScheduleRepository;
import com.mycaretaker.repository.CaretakerRepository;
import com.mycaretaker.repository.ElderlyRepository;

@Service
public class CareScheduleService {

    private final CareScheduleRepository careScheduleRepository;
    private final ElderlyRepository elderlyRepository;
    private final CaretakerRepository caretakerRepository;
    private final NotificationService notificationService;

    public CareScheduleService(
            CareScheduleRepository careScheduleRepository,
            ElderlyRepository elderlyRepository,
            CaretakerRepository caretakerRepository,
            NotificationService notificationService) {

        this.careScheduleRepository = careScheduleRepository;
        this.elderlyRepository = elderlyRepository;
        this.caretakerRepository = caretakerRepository;
        this.notificationService = notificationService;
    }


    public CareSchedule createSchedule(
            int elderlyId,
            int caretakerId,
            CareSchedule schedule) {

        Elderly elderly = elderlyRepository
                .findById(elderlyId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Elderly person not found"));

        Caretaker caretaker = caretakerRepository
                .findById(caretakerId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Caretaker not found"));

        schedule.setElderly(elderly);
        schedule.setCaretaker(caretaker);

        if (schedule.getStatus() == null ||
                schedule.getStatus().isBlank()) {

            schedule.setStatus("SCHEDULED");
        }

        CareSchedule savedSchedule =
                careScheduleRepository.save(schedule);


        // Notify elderly person
        notificationService.createNotification(
                elderly.getUser().getUserId(),

                "Your care schedule has been created by "
                + caretaker.getUser().getFirstName()
                + " "
                + caretaker.getUser().getLastName()
                + " for "
                + schedule.getScheduleDate()
                + " from "
                + schedule.getStartTime()
                + " to "
                + schedule.getEndTime()
        );


        return savedSchedule;
    }


    public List<CareSchedule> getSchedulesForElderly(
            int elderlyId) {

        return careScheduleRepository
                .findByElderlyElderlyId(elderlyId);
    }


    public List<CareSchedule> getSchedulesForCaretaker(
            int caretakerId) {

        return careScheduleRepository
                .findByCaretakerCaretakerId(caretakerId);
    }


    public CareSchedule updateScheduleStatus(
            int scheduleId,
            String status) {

        CareSchedule schedule =
                careScheduleRepository
                        .findById(scheduleId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Care schedule not found"));


        if (!status.equals("SCHEDULED") &&
                !status.equals("COMPLETED") &&
                !status.equals("CANCELLED")) {

            throw new RuntimeException(
                    "Invalid schedule status");
        }


        schedule.setStatus(status);

        CareSchedule updatedSchedule =
                careScheduleRepository.save(schedule);


        String elderlyMessage;


        if (status.equals("COMPLETED")) {

            elderlyMessage =
                    "Your care schedule with "
                    + schedule.getCaretaker()
                            .getUser()
                            .getFirstName()
                    + " "
                    + schedule.getCaretaker()
                            .getUser()
                            .getLastName()
                    + " has been completed.";

        } else if (status.equals("CANCELLED")) {

            elderlyMessage =
                    "Your care schedule with "
                    + schedule.getCaretaker()
                            .getUser()
                            .getFirstName()
                    + " "
                    + schedule.getCaretaker()
                            .getUser()
                            .getLastName()
                    + " has been cancelled.";

        } else {

            elderlyMessage =
                    "Your care schedule status has been changed to "
                    + status
                    + ".";
        }


        notificationService.createNotification(
                schedule.getElderly()
                        .getUser()
                        .getUserId(),

                elderlyMessage
        );


        return updatedSchedule;
    }
}
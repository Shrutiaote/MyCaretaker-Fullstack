package com.mycaretaker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycaretaker.entity.Notification;

public interface NotificationRepository
        extends JpaRepository<Notification, Integer> {

    List<Notification> findByUserUserIdOrderByNotificationDateDesc(
            int userId);

    List<Notification> findByUserUserIdAndReadStatusOrderByNotificationDateDesc(
            int userId,
            boolean readStatus);
}
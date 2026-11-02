package com.mycaretaker.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mycaretaker.entity.Notification;
import com.mycaretaker.entity.User;
import com.mycaretaker.repository.NotificationRepository;
import com.mycaretaker.repository.UserRepository;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;

    public NotificationService(
            NotificationRepository notificationRepository,
            UserRepository userRepository) {

        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    public Notification createNotification(
            int userId,
            String message) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"));

        Notification notification =
                new Notification();

        notification.setUser(user);
        notification.setMessage(message);
        notification.setReadStatus(false);
        notification.setNotificationDate(
                LocalDateTime.now()
        );

        return notificationRepository.save(
                notification
        );
    }

    public List<Notification> getNotificationsForUser(
            int userId) {

        return notificationRepository
                .findByUserUserIdOrderByNotificationDateDesc(
                        userId
                );
    }

    public List<Notification> getUnreadNotifications(
            int userId) {

        return notificationRepository
                .findByUserUserIdAndReadStatusOrderByNotificationDateDesc(
                        userId,
                        false
                );
    }

    public Notification markAsRead(
            int notificationId) {

        Notification notification =
                notificationRepository.findById(
                        notificationId
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Notification not found"
                        ));

        notification.setReadStatus(true);

        return notificationRepository.save(
                notification
        );
    }

    public void markAllAsRead(int userId) {

        List<Notification> notifications =
                notificationRepository
                        .findByUserUserIdAndReadStatusOrderByNotificationDateDesc(
                                userId,
                                false
                        );

        for (Notification notification :
                notifications) {

            notification.setReadStatus(true);
        }

        notificationRepository.saveAll(
                notifications
        );
    }
}
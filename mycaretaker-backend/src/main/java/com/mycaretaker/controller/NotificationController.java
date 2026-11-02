package com.mycaretaker.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mycaretaker.entity.Notification;
import com.mycaretaker.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "http://localhost:5173")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(
            NotificationService notificationService) {

        this.notificationService = notificationService;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>>
            getNotificationsForUser(
                    @PathVariable int userId) {

        return ResponseEntity.ok(
                notificationService
                        .getNotificationsForUser(userId)
        );
    }

    @GetMapping("/user/{userId}/unread")
    public ResponseEntity<List<Notification>>
            getUnreadNotifications(
                    @PathVariable int userId) {

        return ResponseEntity.ok(
                notificationService
                        .getUnreadNotifications(userId)
        );
    }

    @PutMapping("/{notificationId}/read")
    public ResponseEntity<?> markAsRead(
            @PathVariable int notificationId) {

        try {

            Notification notification =
                    notificationService.markAsRead(
                            notificationId
                    );

            return ResponseEntity.ok(notification);

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping("/user/{userId}/read-all")
    public ResponseEntity<?> markAllAsRead(
            @PathVariable int userId) {

        try {

            notificationService.markAllAsRead(
                    userId
            );

            return ResponseEntity.ok(
                    "All notifications marked as read"
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}
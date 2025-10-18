package com.ead.notification.controller;

import com.ead.notification.dtos.NotificationDto;
import com.ead.notification.dtos.NotificationPageDto;
import com.ead.notification.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserNotificationController {

    final NotificationService notificationService;

    public UserNotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/users/{userId}/notifications")
    public ResponseEntity<NotificationPageDto> getAllNotificationsByUser(
            @PathVariable UUID userId,
            Pageable pageable
    ) {
        NotificationPageDto notifications = notificationService.getAllNotificationsByUser(userId, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(notifications);
    }

    @PutMapping("/users/{userId}/notifications/{notificationId}")
    public ResponseEntity<Object> markNotificationAsRead(
            @PathVariable UUID userId,
            @PathVariable UUID notificationId,
            @RequestBody @Valid NotificationDto notificationDto
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(notificationService.updateNotification(notificationDto,
                notificationService.findByNotificationIdAndUserId(notificationId, userId)));
    }

}

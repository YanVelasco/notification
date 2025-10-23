package com.ead.notification.controller;

import com.ead.notification.config.security.UserDetailsImpl;
import com.ead.notification.dtos.NotificationDto;
import com.ead.notification.dtos.NotificationPageDto;
import com.ead.notification.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserNotificationController {

    final NotificationService notificationService;

    public UserNotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/users/{userId}/notifications")
    public ResponseEntity<Object> getAllNotificationsByUser(
            @PathVariable UUID userId,
            Pageable pageable,
            @AuthenticationPrincipal UserDetailsImpl userDetailsImpl
    ) {
        UUID authenticatedUserId = userDetailsImpl.getUserId();

        if (!authenticatedUserId.equals(userId) &&
                !userDetailsImpl.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            throw new AccessDeniedException("Access denied");
        }

        NotificationPageDto notifications = notificationService.getAllNotificationsByUser(userId, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(notifications);

    }

    @PreAuthorize("hasAnyRole('USER')")
    @PutMapping("/users/{userId}/notifications/{notificationId}")
    public ResponseEntity<Object> markNotificationAsRead(
            @PathVariable UUID userId,
            @PathVariable UUID notificationId,
            @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
            @RequestBody @Valid NotificationDto notificationDto
    ) {

        UUID authenticatedUserId = userDetailsImpl.getUserId();

        if (!authenticatedUserId.equals(userId) &&
                !userDetailsImpl.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            throw new AccessDeniedException("Access denied");
        }

        return ResponseEntity.status(HttpStatus.OK).body(notificationService.updateNotification(notificationDto,
                notificationService.findByNotificationIdAndUserId(notificationId, userId)));

    }

}

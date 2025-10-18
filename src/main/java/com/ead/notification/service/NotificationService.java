package com.ead.notification.service;

import com.ead.notification.dtos.NotificationCommandDto;
import com.ead.notification.dtos.NotificationDto;
import com.ead.notification.dtos.NotificationPageDto;
import com.ead.notification.models.NotificationModel;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface NotificationService {

    NotificationModel saveNotification(NotificationCommandDto notificationCommandDto);

    NotificationPageDto getAllNotificationsByUser(UUID userId, Pageable pageable);

    NotificationModel findByNotificationIdAndUserId(UUID notificationId, UUID userId);

    NotificationModel updateNotification(NotificationDto notificationDto, NotificationModel notificationModel);

}

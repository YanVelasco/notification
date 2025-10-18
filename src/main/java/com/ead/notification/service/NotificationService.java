package com.ead.notification.service;

import com.ead.notification.dtos.NotificationCommandDto;
import com.ead.notification.models.NotificationModel;

public interface NotificationService {
    NotificationModel saveNotification(NotificationCommandDto notificationCommandDto);
}

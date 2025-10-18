package com.ead.notification.service.impl;

import com.ead.notification.dtos.NotificationCommandDto;
import com.ead.notification.enums.NotificationStatus;
import com.ead.notification.models.NotificationModel;
import com.ead.notification.repository.NotificationRepository;
import com.ead.notification.service.NotificationService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class NotificationServiceImpl implements NotificationService {

    final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public NotificationModel saveNotification(NotificationCommandDto notificationCommandDto) {
        var notificationModel = new NotificationModel();
        BeanUtils.copyProperties(notificationCommandDto, notificationModel);
        notificationModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        notificationModel.setNotificationStatus(NotificationStatus.CREATED);
        return notificationRepository.save(notificationModel);
    }
}

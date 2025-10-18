package com.ead.notification.service.impl;

import com.ead.notification.dtos.NotificationCommandDto;
import com.ead.notification.dtos.NotificationDto;
import com.ead.notification.dtos.NotificationPageDto;
import com.ead.notification.enums.NotificationStatus;
import com.ead.notification.exceptions.NotFoundException;
import com.ead.notification.models.NotificationModel;
import com.ead.notification.repository.NotificationRepository;
import com.ead.notification.service.NotificationService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

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

    @Override
    public NotificationPageDto getAllNotificationsByUser(UUID userId, Pageable pageable) {
        var notificationsPage = notificationRepository.findAllByUserIdAndNotificationStatus(
                userId,
                NotificationStatus.CREATED,
                pageable
        );
        return NotificationPageDto.from(notificationsPage);
    }

    @Override
    public NotificationModel findByNotificationIdAndUserId(UUID notificationId, UUID userId) {
        var NotificationModel = notificationRepository.findByNotificationIdAndUserId(notificationId, userId);
        if (NotificationModel.isEmpty()) {
            throw new NotFoundException("Notification not found for user");
        }
        return NotificationModel.get();
    }

    @Override
    public NotificationModel updateNotification(NotificationDto notificationDto, NotificationModel notificationModel) {
        notificationModel.setNotificationStatus(notificationDto.notificationStatus());
        return notificationRepository.save(notificationModel);
    }

}

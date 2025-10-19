package com.ead.notification.repository;

import com.ead.notification.enums.NotificationStatus;
import com.ead.notification.models.NotificationModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface NotificationRepository extends MongoRepository<NotificationModel, UUID> {

    Page<NotificationModel> findAllByUserIdAndNotificationStatus(UUID userId, NotificationStatus notificationStatus,
                                                                 Pageable pageable);
}

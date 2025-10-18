package com.ead.notification.repository;

import com.ead.notification.models.NotificationModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface NotificationRepository extends MongoRepository<NotificationModel, UUID> {

}

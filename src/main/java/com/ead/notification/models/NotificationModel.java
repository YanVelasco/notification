package com.ead.notification.models;

import com.ead.notification.enums.NotificationStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "TB_NOTIFICATIONS")
@AllArgsConstructor
@Getter
@Setter
public class NotificationModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Field("notification_id")
    private UUID notificationId;

    @NotNull(message = "UserId cannot be null")
    @Field("user_id")
    private UUID userId;

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 150)
    @Field("title")
    private String title;

    @NotBlank(message = "Message cannot be blank")
    @Field("message")
    private String message;

    @NotNull(message = "CreationDate cannot be null")
    @Field("creation_date")
    private LocalDateTime creationDate;

    @NotNull(message = "NotificationStatus cannot be null")
    @Field(value = "notification_status", targetType = FieldType.STRING)
    private NotificationStatus notificationStatus;

    public NotificationModel() {
        this.notificationId = UUID.randomUUID();
    }

}
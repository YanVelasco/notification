package com.ead.notification.dtos;

import com.ead.notification.models.NotificationModel;
import org.springframework.data.domain.Page;

import java.util.List;

public record NotificationPageDto(
        List<NotificationModel> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages,
        boolean last,
        boolean first,
        boolean empty
) {
    public static NotificationPageDto from(Page<NotificationModel> page) {
        return new NotificationPageDto(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isLast(),
                page.isFirst(),
                page.isEmpty()
        );
    }
}

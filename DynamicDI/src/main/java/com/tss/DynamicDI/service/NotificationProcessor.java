package com.tss.DynamicDI.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationProcessor {

    private final Map<String,NotificationService> notifications;
    private final NotificationService defaultNotificationService;
    public void sendNotification(String type,String message, String recipient) {
        NotificationService notificationService =
                notifications.getOrDefault(type, defaultNotificationService);
        notificationService.sendNotification(message, recipient);
    }

}

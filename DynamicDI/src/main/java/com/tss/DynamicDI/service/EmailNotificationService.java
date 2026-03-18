package com.tss.DynamicDI.service;


import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("email")
@Primary
public class EmailNotificationService implements NotificationService {
    @Override
    public void sendNotification(String message, String recipient) {
        System.out.println("Email sent to " + recipient + " : " + message);
    }
}

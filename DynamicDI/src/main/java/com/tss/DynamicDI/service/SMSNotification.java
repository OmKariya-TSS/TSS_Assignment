package com.tss.DynamicDI.service;

import org.springframework.stereotype.Service;

@Service("sms")
public class SMSNotification implements NotificationService {

    @Override
    public void sendNotification(String message, String recipient) {
        System.out.println("SMS sent to " + recipient + " : " + message);
    }
}
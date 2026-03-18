package com.tss.DynamicDI.service;


import org.springframework.stereotype.Service;

@Service("Whatsapp")
public class WhatsappNotification implements NotificationService{
    @Override
    public void sendNotification(String message, String recipient) {
        System.out.println("whatapp notification sent to : "+recipient+" :"+message);
    }
}

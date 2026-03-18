package com.tss.DynamicDI.controller;


import com.tss.DynamicDI.service.NotificationProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationProcessor notificationProcessor;

    @PutMapping("/send")
    public void sendNotification(@RequestParam String message, @RequestParam String recipient,@RequestParam String type) {
        notificationProcessor.sendNotification(type,message,recipient);
    }

}

package com.ororura.audiomarket.controllers;

import com.ororura.audiomarket.entities.Notification;
import com.ororura.audiomarket.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PreAuthorize("hasRole('user')")
    @GetMapping
    public ResponseEntity<List<Notification>> getNotifications() {
        return new ResponseEntity<>(this.notificationService.findAllNotifications(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('user')")
    @MessageMapping("/createProduct")
    @SendTo("/topic/notifications")
    public List<Notification> getNotification() {
        return this.notificationService.findAllNotifications();
    }

}

package com.ororura.autiomarket.controllers;

import com.ororura.autiomarket.entities.Notification;
import com.ororura.autiomarket.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/notifications")
    public ResponseEntity<List<Notification>> getNotifications() {
        return new ResponseEntity<>(this.notificationService.findAllNotifications(), HttpStatus.OK);
    }

}

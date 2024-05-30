package com.ororura.autiomarket.services;

import com.ororura.autiomarket.entities.Notification;
import com.ororura.autiomarket.repositories.NotificationRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepo notificationRepo;

    @Autowired
    public NotificationService(NotificationRepo notificationRepo) {
        this.notificationRepo = notificationRepo;
    }

    @Transactional
    public List<Notification> findAllNotifications() {
        return this.notificationRepo.findAll();
    }

    @Transactional
    public void saveNotifications(Notification notification) {
        this.notificationRepo.save(notification);
    }
}

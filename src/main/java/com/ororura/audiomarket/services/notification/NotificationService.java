package com.ororura.audiomarket.services.notification;

import com.ororura.audiomarket.entities.Notification;
import com.ororura.audiomarket.repositories.NotificationRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepo notificationRepo;

    public NotificationService(NotificationRepo notificationRepo) {
        this.notificationRepo = notificationRepo;
    }

    @Transactional
    public void saveNotifications(Notification notification) {
        this.notificationRepo.save(notification);
    }

    public List<Notification> findAllNotifications() {
        return this.notificationRepo.findAll();
    }
}

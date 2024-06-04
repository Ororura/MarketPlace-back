package com.ororura.autiomarket.repositories;

import com.ororura.autiomarket.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepo extends JpaRepository<Notification, Long> {
}

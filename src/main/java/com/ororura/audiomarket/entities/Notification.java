package com.ororura.audiomarket.entities;

import com.ororura.audiomarket.utils.NotificationStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @JoinColumn(name = "id")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Product product;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private NotificationStatus status;
}

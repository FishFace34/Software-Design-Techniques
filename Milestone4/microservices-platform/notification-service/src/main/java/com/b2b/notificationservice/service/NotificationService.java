package com.b2b.notificationservice.service;

import com.b2b.notificationservice.domain.Notification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class NotificationService {
    private final Map<String, Notification> notifications = new ConcurrentHashMap<>();

    public Notification createNotification(String event, String message) {
        Notification notification = new Notification(event, message);
        notifications.put(notification.getId(), notification);
        
        // Log notification
        System.out.println("[NOTIFY] " + notification.getMessage() + " (Event: " + event + ")");
        
        return notification;
    }

    public Optional<Notification> getNotificationById(String id) {
        return Optional.ofNullable(notifications.get(id));
    }

    public List<Notification> getAllNotifications() {
        return new ArrayList<>(notifications.values());
    }

    public List<Notification> getNotificationsByEvent(String event) {
        return notifications.values().stream()
                .filter(n -> n.getEvent().equals(event))
                .sorted(Comparator.comparing(Notification::getTimestamp).reversed())
                .collect(Collectors.toList());
    }

    public void deleteNotification(String id) {
        notifications.remove(id);
    }

    public void deleteAllNotifications() {
        notifications.clear();
    }
}


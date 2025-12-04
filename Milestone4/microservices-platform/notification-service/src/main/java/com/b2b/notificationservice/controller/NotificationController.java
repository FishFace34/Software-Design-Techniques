package com.b2b.notificationservice.controller;

import com.b2b.notificationservice.domain.Notification;
import com.b2b.notificationservice.dto.NotificationRequest;
import com.b2b.notificationservice.dto.NotificationResponse;
import com.b2b.notificationservice.service.NotificationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<NotificationResponse> createNotification(@Valid @RequestBody NotificationRequest request) {
        Notification notification = notificationService.createNotification(request.getEvent(), request.getMessage());
        NotificationResponse response = new NotificationResponse(
                notification.getId(),
                notification.getEvent(),
                notification.getMessage(),
                notification.getTimestamp()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationResponse> getNotification(@PathVariable String id) {
        return notificationService.getNotificationById(id)
                .map(n -> new NotificationResponse(n.getId(), n.getEvent(), n.getMessage(), n.getTimestamp()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getAllNotifications(
            @RequestParam(required = false) String event) {
        List<Notification> notifications = (event != null && !event.isEmpty())
                ? notificationService.getNotificationsByEvent(event)
                : notificationService.getAllNotifications();
        
        List<NotificationResponse> responses = notifications.stream()
                .map(n -> new NotificationResponse(n.getId(), n.getEvent(), n.getMessage(), n.getTimestamp()))
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable String id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllNotifications() {
        notificationService.deleteAllNotifications();
        return ResponseEntity.noContent().build();
    }
}


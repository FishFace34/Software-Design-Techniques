package com.b2b.notificationservice.dto;

import java.time.LocalDateTime;

public class NotificationResponse {
    private String id;
    private String event;
    private String message;
    private LocalDateTime timestamp;

    public NotificationResponse() {
    }

    public NotificationResponse(String id, String event, String message, LocalDateTime timestamp) {
        this.id = id;
        this.event = event;
        this.message = message;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}


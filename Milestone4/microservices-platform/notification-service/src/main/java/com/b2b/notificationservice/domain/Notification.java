package com.b2b.notificationservice.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Notification {
    private final String id;
    private final String event;
    private final String message;
    private final LocalDateTime timestamp;

    public Notification(String event, String message) {
        this.id = UUID.randomUUID().toString();
        this.event = event;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getEvent() {
        return event;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}


package com.b2b.notificationservice.dto;

import jakarta.validation.constraints.NotBlank;

public class NotificationRequest {
    @NotBlank(message = "Event is required")
    private String event;
    
    @NotBlank(message = "Message is required")
    private String message;

    // Getters and Setters
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
}


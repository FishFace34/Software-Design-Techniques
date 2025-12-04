package com.b2b.listingservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class NotificationClient {
    private final RestTemplate restTemplate;
    private final String notificationServiceUrl;

    public NotificationClient(RestTemplate restTemplate, 
                             @Value("${notification.service.url}") String notificationServiceUrl) {
        this.restTemplate = restTemplate;
        this.notificationServiceUrl = notificationServiceUrl;
    }

    public void sendNotification(String event, String message) {
        try {
            String url = notificationServiceUrl + "/api/notifications";
            
            Map<String, String> payload = new HashMap<>();
            payload.put("event", event);
            payload.put("message", message);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            HttpEntity<Map<String, String>> request = new HttpEntity<>(payload, headers);
            
            restTemplate.postForObject(url, request, Void.class);
        } catch (Exception e) {
            // Log error but don't fail the main operation
            System.err.println("Failed to send notification: " + e.getMessage());
        }
    }
}


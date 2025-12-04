package com.b2b.observer;

public class NotificationService implements Observer {
    @Override
    public void update(String event, String message) {
        System.out.println("[NOTIFY] " + message);
    }
}


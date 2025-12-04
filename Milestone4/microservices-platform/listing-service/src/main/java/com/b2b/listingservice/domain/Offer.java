package com.b2b.listingservice.domain;

import java.time.LocalDateTime;

public class Offer {
    private final String buyerId;
    private final Money amount;
    private final LocalDateTime timestamp;
    private final String listingId;

    public Offer(String buyerId, Money amount, String listingId) {
        this.buyerId = buyerId;
        this.amount = amount;
        this.listingId = listingId;
        this.timestamp = LocalDateTime.now();
    }

    public String getBuyerId() {
        return buyerId;
    }

    public Money getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getListingId() {
        return listingId;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "buyerId='" + buyerId + '\'' +
                ", amount=" + amount +
                ", timestamp=" + timestamp +
                ", listingId='" + listingId + '\'' +
                '}';
    }
}


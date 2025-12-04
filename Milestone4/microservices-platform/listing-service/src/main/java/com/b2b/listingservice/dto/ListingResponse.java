package com.b2b.listingservice.dto;

import com.b2b.listingservice.domain.Listing;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ListingResponse {
    private String id;
    private String title;
    private String location;
    private double surface;
    private String zoning;
    private BigDecimal priceAmount;
    private String currency;
    private LocalDateTime createdAt;
    private String state;

    public ListingResponse() {
    }

    public ListingResponse(Listing listing) {
        this.id = listing.getId();
        this.title = listing.getTitle();
        this.location = listing.getLocation();
        this.surface = listing.getSurface();
        this.zoning = listing.getZoning();
        this.priceAmount = listing.getPrice().getAmount();
        this.currency = listing.getPrice().getCurrency().getCurrencyCode();
        this.createdAt = listing.getCreatedAt();
        this.state = listing.getStateName();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public String getZoning() {
        return zoning;
    }

    public void setZoning(String zoning) {
        this.zoning = zoning;
    }

    public BigDecimal getPriceAmount() {
        return priceAmount;
    }

    public void setPriceAmount(BigDecimal priceAmount) {
        this.priceAmount = priceAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}


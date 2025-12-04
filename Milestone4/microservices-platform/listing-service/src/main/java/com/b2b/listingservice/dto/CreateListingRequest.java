package com.b2b.listingservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateListingRequest {
    @NotBlank(message = "Title is required")
    private String title;
    
    @NotBlank(message = "Location is required")
    private String location;
    
    @NotNull(message = "Surface is required")
    @Positive(message = "Surface must be positive")
    private Double surface;
    
    @NotBlank(message = "Zoning is required")
    private String zoning;
    
    @NotNull(message = "Price amount is required")
    @Positive(message = "Price must be positive")
    private Double priceAmount;
    
    @NotBlank(message = "Currency is required")
    private String currency;

    // Getters and Setters
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

    public Double getSurface() {
        return surface;
    }

    public void setSurface(Double surface) {
        this.surface = surface;
    }

    public String getZoning() {
        return zoning;
    }

    public void setZoning(String zoning) {
        this.zoning = zoning;
    }

    public Double getPriceAmount() {
        return priceAmount;
    }

    public void setPriceAmount(Double priceAmount) {
        this.priceAmount = priceAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}


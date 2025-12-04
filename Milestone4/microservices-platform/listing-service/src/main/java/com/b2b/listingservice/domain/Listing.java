package com.b2b.listingservice.domain;

import com.b2b.listingservice.state.DraftState;
import com.b2b.listingservice.state.ListingState;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Listing {
    private final String id;
    private final String title;
    private final String location;
    private final double surface;
    private final String zoning;
    private final Money price;
    private final LocalDateTime createdAt;
    private ListingState state;
    private final List<Offer> offers;

    public Listing(String id, String title, String location, double surface, 
                   String zoning, Money price) {
        if (id == null || title == null || location == null || 
            zoning == null || price == null) {
            throw new IllegalArgumentException("Required fields cannot be null");
        }
        this.id = id;
        this.title = title;
        this.location = location;
        this.surface = surface;
        this.zoning = zoning;
        this.price = price;
        this.createdAt = LocalDateTime.now();
        this.state = new DraftState();
        this.offers = new ArrayList<>();
    }

    public void submitForReview() {
        state.submitForReview(this);
    }

    public void publish() {
        state.publish(this);
    }

    public void setState(ListingState state) {
        this.state = state;
    }

    public ListingState getState() {
        return state;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public double getSurface() {
        return surface;
    }

    public String getZoning() {
        return zoning;
    }

    public Money getPrice() {
        return price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<Offer> getOffers() {
        return new ArrayList<>(offers);
    }

    public void addOffer(Offer offer) {
        offers.add(offer);
        if (state instanceof com.b2b.listingservice.state.PublishedState) {
            ((com.b2b.listingservice.state.PublishedState) state).receiveOffer(this);
        }
    }

    public String getStateName() {
        return state.getClass().getSimpleName();
    }
}


package com.b2b.builder;

import com.b2b.domain.Listing;
import com.b2b.domain.Money;

import java.util.Currency;
import java.util.UUID;

public class ListingBuilder {
    private String id;
    private String title;
    private String location;
    private Double surface;
    private String zoning;
    private Money price;

    public ListingBuilder() {
        this.id = UUID.randomUUID().toString();
    }

    public ListingBuilder withId(String id) {
        this.id = id;
        return this;
    }

    public ListingBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public ListingBuilder withLocation(String location) {
        this.location = location;
        return this;
    }

    public ListingBuilder withSurface(double surface) {
        this.surface = surface;
        return this;
    }

    public ListingBuilder withZoning(String zoning) {
        this.zoning = zoning;
        return this;
    }

    public ListingBuilder withPrice(double amount, Currency currency) {
        this.price = new Money(amount, currency);
        return this;
    }

    public ListingBuilder withPrice(Money price) {
        this.price = price;
        return this;
    }

    public Listing build() {
        if (title == null) {
            throw new IllegalStateException("Title is required");
        }
        if (location == null) {
            throw new IllegalStateException("Location is required");
        }
        if (surface == null) {
            throw new IllegalStateException("Surface is required");
        }
        if (zoning == null) {
            throw new IllegalStateException("Zoning is required");
        }
        if (price == null) {
            throw new IllegalStateException("Price is required");
        }
        return new Listing(id, title, location, surface, zoning, price);
    }
}


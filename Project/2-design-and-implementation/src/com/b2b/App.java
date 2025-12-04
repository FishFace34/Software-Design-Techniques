package com.b2b;

import com.b2b.builder.ListingBuilder;
import com.b2b.domain.Listing;
import com.b2b.domain.Money;
import com.b2b.observer.NotificationService;
import com.b2b.strategy.PriceAscSort;
import com.b2b.strategy.SearchService;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class App {
    public static void main(String[] args) {
        System.out.println("=== B2B Land Listing Platform - Milestone 2 ===\n");

        // ===== BUILDER PATTERN =====
        System.out.println("--- Builder Pattern: Creating Listing ---");
        Listing listing = new ListingBuilder()
                .withTitle("Prime Commercial Land - Downtown")
                .withLocation("123 Business District, Metro City")
                .withSurface(5000.0)
                .withZoning("Commercial")
                .withPrice(2500000.0, Currency.getInstance("USD"))
                .build();
        System.out.println("Created: " + listing);
        System.out.println();

        // ===== OBSERVER PATTERN =====
        System.out.println("--- Observer Pattern: Attaching Notification Service ---");
        NotificationService notificationService = new NotificationService();
        listing.attach(notificationService);
        System.out.println("Notification service attached to listing.\n");

        // ===== STATE PATTERN =====
        System.out.println("--- State Pattern: State Transitions ---");
        System.out.println("Current state: " + listing.getState().getClass().getSimpleName());
        System.out.println();
        
        System.out.println("1. Submitting listing for review...");
        listing.submitForReview();
        System.out.println("Current state: " + listing.getState().getClass().getSimpleName());
        System.out.println();

        System.out.println("2. Publishing listing...");
        listing.publish();
        System.out.println("Current state: " + listing.getState().getClass().getSimpleName());
        System.out.println();

        // ===== STRATEGY PATTERN =====
        System.out.println("--- Strategy Pattern: Sorting Listings ---");
        
        // Create additional listings for sorting demonstration
        Listing listing2 = new ListingBuilder()
                .withTitle("Industrial Zone Land")
                .withLocation("456 Industrial Park, Metro City")
                .withSurface(10000.0)
                .withZoning("Industrial")
                .withPrice(1800000.0, Currency.getInstance("USD"))
                .build();

        Listing listing3 = new ListingBuilder()
                .withTitle("Residential Development Land")
                .withLocation("789 Suburban Area, Metro City")
                .withSurface(7500.0)
                .withZoning("Residential")
                .withPrice(3200000.0, Currency.getInstance("USD"))
                .build();

        List<Listing> listings = new ArrayList<>();
        listings.add(listing);
        listings.add(listing2);
        listings.add(listing3);

        System.out.println("Original listings order:");
        for (int i = 0; i < listings.size(); i++) {
            System.out.println((i + 1) + ". " + listings.get(i).getTitle() + 
                             " - Price: " + listings.get(i).getPrice());
        }
        System.out.println();

        System.out.println("Using PriceAscSort strategy...");
        SearchService searchService = new SearchService(new PriceAscSort());
        List<Listing> sortedListings = searchService.search(listings);

        System.out.println("Sorted listings (by price, ascending):");
        for (int i = 0; i < sortedListings.size(); i++) {
            System.out.println((i + 1) + ". " + sortedListings.get(i).getTitle() + 
                             " - Price: " + sortedListings.get(i).getPrice());
        }
        System.out.println();

        System.out.println("=== All patterns demonstrated successfully! ===");
    }
}


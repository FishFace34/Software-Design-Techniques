package com.b2b.listingservice.service;

import com.b2b.listingservice.builder.ListingBuilder;
import com.b2b.listingservice.client.NotificationClient;
import com.b2b.listingservice.domain.Listing;
import com.b2b.listingservice.domain.Money;
import com.b2b.listingservice.domain.Offer;
import com.b2b.listingservice.dto.CreateListingRequest;
import com.b2b.listingservice.dto.CreateOfferRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class ListingService {
    private final Map<String, Listing> listings = new ConcurrentHashMap<>();
    private final NotificationClient notificationClient;

    public ListingService(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    public Listing createListing(CreateListingRequest request) {
        Listing listing = new ListingBuilder()
                .withTitle(request.getTitle())
                .withLocation(request.getLocation())
                .withSurface(request.getSurface())
                .withZoning(request.getZoning())
                .withPrice(request.getPriceAmount(), 
                          java.util.Currency.getInstance(request.getCurrency()))
                .build();
        
        listings.put(listing.getId(), listing);
        return listing;
    }

    public Optional<Listing> getListingById(String id) {
        return Optional.ofNullable(listings.get(id));
    }

    public List<Listing> getAllListings() {
        return new ArrayList<>(listings.values());
    }

    public List<Listing> getListingsByZoning(String zoning) {
        return listings.values().stream()
                .filter(listing -> listing.getZoning().equalsIgnoreCase(zoning))
                .collect(Collectors.toList());
    }

    public Listing submitForReview(String id) {
        Listing listing = listings.get(id);
        if (listing == null) {
            throw new IllegalArgumentException("Listing not found: " + id);
        }
        
        String oldState = listing.getStateName();
        listing.submitForReview();
        String newState = listing.getStateName();
        
        if (!oldState.equals(newState)) {
            notificationClient.sendNotification("SUBMITTED_FOR_REVIEW", 
                "Listing '" + listing.getTitle() + "' (" + id + ") submitted for review");
        }
        
        return listing;
    }

    public Listing publish(String id) {
        Listing listing = listings.get(id);
        if (listing == null) {
            throw new IllegalArgumentException("Listing not found: " + id);
        }
        
        String oldState = listing.getStateName();
        listing.publish();
        String newState = listing.getStateName();
        
        if (!oldState.equals(newState)) {
            notificationClient.sendNotification("PUBLISHED", 
                "Listing '" + listing.getTitle() + "' (" + id + ") published");
        }
        
        return listing;
    }

    public Listing addOffer(String listingId, CreateOfferRequest request) {
        Listing listing = listings.get(listingId);
        if (listing == null) {
            throw new IllegalArgumentException("Listing not found: " + listingId);
        }
        
        Money offerAmount = new Money(request.getAmount(), 
                                     java.util.Currency.getInstance(request.getCurrency()));
        Offer offer = new Offer(request.getBuyerId(), offerAmount, listingId);
        listing.addOffer(offer);
        
        notificationClient.sendNotification("OFFER_RECEIVED", 
            "Offer received for listing '" + listing.getTitle() + "' (" + listingId + ") from buyer " + request.getBuyerId());
        
        return listing;
    }

    public void deleteListing(String id) {
        Listing listing = listings.remove(id);
        if (listing != null) {
            notificationClient.sendNotification("DELETED", 
                "Listing '" + listing.getTitle() + "' (" + id + ") deleted");
        }
    }
}


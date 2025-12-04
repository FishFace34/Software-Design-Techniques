package com.b2b.state;

import com.b2b.domain.Listing;

public class PublishedState implements ListingState {
    @Override
    public void submitForReview(Listing listing) {
        System.out.println("Listing is already published. Cannot submit for review again.");
    }

    @Override
    public void publish(Listing listing) {
        System.out.println("Listing is already published.");
    }

    public void receiveOffer(Listing listing) {
        System.out.println("State: Published -> UnderOffer");
        listing.setState(new UnderOfferState());
        listing.emit("OfferReceived");
    }
}


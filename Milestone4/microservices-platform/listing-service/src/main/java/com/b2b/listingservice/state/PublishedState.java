package com.b2b.listingservice.state;

import com.b2b.listingservice.domain.Listing;

public class PublishedState implements ListingState {
    @Override
    public void submitForReview(Listing listing) {
        throw new IllegalStateException("Listing is already published. Cannot submit for review again.");
    }

    @Override
    public void publish(Listing listing) {
        // Already published
    }

    public void receiveOffer(Listing listing) {
        listing.setState(new UnderOfferState());
    }
}


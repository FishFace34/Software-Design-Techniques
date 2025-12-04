package com.b2b.listingservice.state;

import com.b2b.listingservice.domain.Listing;

public class UnderOfferState implements ListingState {
    @Override
    public void submitForReview(Listing listing) {
        throw new IllegalStateException("Listing is under offer. Cannot submit for review.");
    }

    @Override
    public void publish(Listing listing) {
        throw new IllegalStateException("Listing is under offer. Cannot republish.");
    }
}


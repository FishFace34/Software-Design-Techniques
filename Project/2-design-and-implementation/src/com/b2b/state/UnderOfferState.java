package com.b2b.state;

import com.b2b.domain.Listing;

public class UnderOfferState implements ListingState {
    @Override
    public void submitForReview(Listing listing) {
        System.out.println("Listing is under offer. Cannot submit for review.");
    }

    @Override
    public void publish(Listing listing) {
        System.out.println("Listing is under offer. Cannot republish.");
    }
}


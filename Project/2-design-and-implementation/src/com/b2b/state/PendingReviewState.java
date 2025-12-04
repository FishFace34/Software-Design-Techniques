package com.b2b.state;

import com.b2b.domain.Listing;

public class PendingReviewState implements ListingState {
    @Override
    public void submitForReview(Listing listing) {
        System.out.println("Listing is already pending review.");
    }

    @Override
    public void publish(Listing listing) {
        System.out.println("State: PendingReview -> Published");
        listing.setState(new PublishedState());
        listing.emit("Published");
    }
}


package com.b2b.listingservice.state;

import com.b2b.listingservice.domain.Listing;

public class DraftState implements ListingState {
    @Override
    public void submitForReview(Listing listing) {
        listing.setState(new PendingReviewState());
    }

    @Override
    public void publish(Listing listing) {
        throw new IllegalStateException("Cannot publish from Draft state. Please submit for review first.");
    }
}


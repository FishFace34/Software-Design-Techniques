package com.b2b.listingservice.state;

import com.b2b.listingservice.domain.Listing;

public class PendingReviewState implements ListingState {
    @Override
    public void submitForReview(Listing listing) {
        // Already pending review
    }

    @Override
    public void publish(Listing listing) {
        listing.setState(new PublishedState());
    }
}


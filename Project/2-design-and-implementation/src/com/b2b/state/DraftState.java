package com.b2b.state;

import com.b2b.domain.Listing;

public class DraftState implements ListingState {
    @Override
    public void submitForReview(Listing listing) {
        System.out.println("State: Draft -> PendingReview");
        listing.setState(new PendingReviewState());
        listing.emit("SubmittedForReview");
    }

    @Override
    public void publish(Listing listing) {
        System.out.println("Cannot publish from Draft state. Please submit for review first.");
    }
}


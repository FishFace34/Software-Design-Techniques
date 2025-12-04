package com.b2b.listingservice.state;

import com.b2b.listingservice.domain.Listing;

public interface ListingState {
    void submitForReview(Listing listing);
    void publish(Listing listing);
}


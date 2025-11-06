package com.b2b.state;

import com.b2b.domain.Listing;

public interface ListingState {
    void submitForReview(Listing listing);
    void publish(Listing listing);
}


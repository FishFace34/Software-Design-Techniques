package com.b2b.strategy;

import com.b2b.domain.Listing;

import java.util.List;

public class SearchService {
    private SortStrategy strategy;

    public SearchService(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Listing> search(List<Listing> listings) {
        if (strategy == null) {
            throw new IllegalStateException("Sort strategy must be set");
        }
        return strategy.sort(listings);
    }
}


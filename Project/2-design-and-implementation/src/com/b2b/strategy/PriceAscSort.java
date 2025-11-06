package com.b2b.strategy;

import com.b2b.domain.Listing;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PriceAscSort implements SortStrategy {
    @Override
    public List<Listing> sort(List<Listing> listings) {
        List<Listing> sorted = new ArrayList<>(listings);
        sorted.sort(Comparator.comparing(listing -> listing.getPrice().getAmount()));
        return sorted;
    }
}


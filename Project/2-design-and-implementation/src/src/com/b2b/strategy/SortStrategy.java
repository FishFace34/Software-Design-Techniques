package com.b2b.strategy;

import com.b2b.domain.Listing;

import java.util.List;

public interface SortStrategy {
    List<Listing> sort(List<Listing> listings);
}


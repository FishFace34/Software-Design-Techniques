package com.b2b.searchservice.strategy;

import com.b2b.searchservice.dto.ListingDTO;

import java.util.List;

public interface SortStrategy {
    List<ListingDTO> sort(List<ListingDTO> listings);
}


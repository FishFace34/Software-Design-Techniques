package com.b2b.searchservice.strategy;

import com.b2b.searchservice.dto.ListingDTO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PriceAscSort implements SortStrategy {
    @Override
    public List<ListingDTO> sort(List<ListingDTO> listings) {
        List<ListingDTO> sorted = new ArrayList<>(listings);
        sorted.sort(Comparator.comparing(ListingDTO::getPriceAmount));
        return sorted;
    }
}


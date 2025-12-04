package com.b2b.searchservice.service;

import com.b2b.searchservice.client.ListingClient;
import com.b2b.searchservice.dto.ListingDTO;
import com.b2b.searchservice.strategy.PriceAscSort;
import com.b2b.searchservice.strategy.PriceDescSort;
import com.b2b.searchservice.strategy.RecencySort;
import com.b2b.searchservice.strategy.SortStrategy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final ListingClient listingClient;

    public SearchService(ListingClient listingClient) {
        this.listingClient = listingClient;
    }

    public List<ListingDTO> search(String sortBy, String zoning, 
                                   BigDecimal minPrice, BigDecimal maxPrice,
                                   Double minSurface, Double maxSurface) {
        // Fetch listings from Listing Service
        List<ListingDTO> listings = (zoning != null && !zoning.isEmpty()) 
                ? listingClient.getListingsByZoning(zoning)
                : listingClient.getAllListings();

        // Apply filters
        if (minPrice != null) {
            listings = listings.stream()
                    .filter(l -> l.getPriceAmount().compareTo(minPrice) >= 0)
                    .collect(Collectors.toList());
        }
        if (maxPrice != null) {
            listings = listings.stream()
                    .filter(l -> l.getPriceAmount().compareTo(maxPrice) <= 0)
                    .collect(Collectors.toList());
        }
        if (minSurface != null) {
            listings = listings.stream()
                    .filter(l -> l.getSurface() >= minSurface)
                    .collect(Collectors.toList());
        }
        if (maxSurface != null) {
            listings = listings.stream()
                    .filter(l -> l.getSurface() <= maxSurface)
                    .collect(Collectors.toList());
        }

        // Apply sorting strategy
        SortStrategy strategy = getSortStrategy(sortBy);
        return strategy.sort(listings);
    }

    private SortStrategy getSortStrategy(String sortBy) {
        if (sortBy == null || sortBy.isEmpty()) {
            return new RecencySort(); // Default
        }
        
        return switch (sortBy.toLowerCase()) {
            case "price_asc" -> new PriceAscSort();
            case "price_desc" -> new PriceDescSort();
            case "recency" -> new RecencySort();
            default -> new RecencySort();
        };
    }
}


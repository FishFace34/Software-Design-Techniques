package com.b2b.searchservice.controller;

import com.b2b.searchservice.dto.ListingDTO;
import com.b2b.searchservice.service.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public ResponseEntity<List<ListingDTO>> search(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String zoning,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Double minSurface,
            @RequestParam(required = false) Double maxSurface) {
        
        List<ListingDTO> results = searchService.search(
                sortBy, zoning, minPrice, maxPrice, minSurface, maxSurface);
        return ResponseEntity.ok(results);
    }
}


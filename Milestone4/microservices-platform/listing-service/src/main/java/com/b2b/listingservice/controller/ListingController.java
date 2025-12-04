package com.b2b.listingservice.controller;

import com.b2b.listingservice.domain.Listing;
import com.b2b.listingservice.dto.CreateListingRequest;
import com.b2b.listingservice.dto.CreateOfferRequest;
import com.b2b.listingservice.dto.ListingResponse;
import com.b2b.listingservice.service.ListingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/listings")
public class ListingController {
    private final ListingService listingService;

    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @PostMapping
    public ResponseEntity<ListingResponse> createListing(@Valid @RequestBody CreateListingRequest request) {
        Listing listing = listingService.createListing(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ListingResponse(listing));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListingResponse> getListing(@PathVariable String id) {
        return listingService.getListingById(id)
                .map(listing -> ResponseEntity.ok(new ListingResponse(listing)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ListingResponse>> getAllListings() {
        List<ListingResponse> listings = listingService.getAllListings().stream()
                .map(ListingResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(listings);
    }

    @GetMapping("/zoning/{zoning}")
    public ResponseEntity<List<ListingResponse>> getListingsByZoning(@PathVariable String zoning) {
        List<ListingResponse> listings = listingService.getListingsByZoning(zoning).stream()
                .map(ListingResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(listings);
    }

    @PostMapping("/{id}/submit-for-review")
    public ResponseEntity<ListingResponse> submitForReview(@PathVariable String id) {
        try {
            Listing listing = listingService.submitForReview(id);
            return ResponseEntity.ok(new ListingResponse(listing));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/publish")
    public ResponseEntity<ListingResponse> publish(@PathVariable String id) {
        try {
            Listing listing = listingService.publish(id);
            return ResponseEntity.ok(new ListingResponse(listing));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/offers")
    public ResponseEntity<ListingResponse> addOffer(@PathVariable String id, 
                                                   @Valid @RequestBody CreateOfferRequest request) {
        try {
            Listing listing = listingService.addOffer(id, request);
            return ResponseEntity.ok(new ListingResponse(listing));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteListing(@PathVariable String id) {
        listingService.deleteListing(id);
        return ResponseEntity.noContent().build();
    }
}


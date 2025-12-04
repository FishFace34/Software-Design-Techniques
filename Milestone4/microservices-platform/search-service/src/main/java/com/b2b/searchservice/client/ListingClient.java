package com.b2b.searchservice.client;

import com.b2b.searchservice.dto.ListingDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class ListingClient {
    private final RestTemplate restTemplate;
    private final String listingServiceUrl;

    public ListingClient(RestTemplate restTemplate,
                        @Value("${listing.service.url}") String listingServiceUrl) {
        this.restTemplate = restTemplate;
        this.listingServiceUrl = listingServiceUrl;
    }

    public List<ListingDTO> getAllListings() {
        try {
            return restTemplate.exchange(
                    listingServiceUrl + "/api/listings",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<ListingDTO>>() {}
            ).getBody();
        } catch (Exception e) {
            System.err.println("Failed to fetch listings: " + e.getMessage());
            return List.of();
        }
    }

    public List<ListingDTO> getListingsByZoning(String zoning) {
        try {
            return restTemplate.exchange(
                    listingServiceUrl + "/api/listings/zoning/" + zoning,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<ListingDTO>>() {}
            ).getBody();
        } catch (Exception e) {
            System.err.println("Failed to fetch listings by zoning: " + e.getMessage());
            return List.of();
        }
    }
}


# Milestone 1 – B2B Land Listing Platform

## Team Members
- Batuhan Kamburoglu 1241EA

---

## Project Description
We will implement a **B2B Land Listing Platform** designed for corporate sellers (brokers, agencies, funds) to publish land listings and for corporate buyers (developers, investors) to search, filter, and send offers.  

### Main Features
- **Listing Management:** Sellers create listings with details (location, zoning, surface, utilities, documents, price, currency).  
- **Advanced Search & Filtering:** Buyers can search by price range, surface, zoning, location, and infrastructure.  
- **Listing Lifecycle:** Listings go through states: Draft → Pending Review → Published → Under Offer → Sold → Archived.  
- **Offer / RFQ Flow:** Buyers place offers; sellers can accept, reject, or counter-offer. Negotiations are logged.  
- **Notifications:** Buyers and sellers receive updates about listing status and offers.  
- **Integrations:** Map/GIS overlays and external KYC/document verification services.  

This project will focus on scalability, maintainability, and integration. It provides a clear structure to support the next milestones (UML, architecture comparison, microservices).

---

## Design Patterns and Justification

### 1. State
- **Problem:** A listing can be Draft, Pending Review, Published, Under Offer, Sold, or Archived. Actions depend on the state.  
- **Solution:** Use the **State pattern** with specific classes (`DraftState`, `PublishedState`, etc.) to encapsulate behavior.  
- **Advantage:** Avoids large if-else chains; easier to add new states compared to switch statements.

### 2. Strategy
- **Problem:** Search results may need different sorting (by price, recency, relevance) and pricing/commission rules can vary.  
- **Solution:** Define a `Strategy` interface with multiple implementations (`PriceAsc`, `RecencySort`, `FlatCommission`, `TieredCommission`).  
- **Advantage:** New algorithms can be added without modifying existing code; cleaner than hardcoded conditionals.

### 3. Observer
- **Problem:** Buyers and sellers must be notified when a listing or offer changes.  
- **Solution:** Apply the **Observer pattern** where `Listing`/`Offer` are subjects and `NotificationService` is an observer.  
- **Advantage:** Decouples core logic from notification logic; easier to add new observers (e.g., email, SMS) than with direct calls.

### 4. Builder
- **Problem:** Creating a listing involves many optional and mandatory fields (location, zoning, documents, utilities, premium options).  
- **Solution:** Use the **Builder pattern** to construct listings step by step, validating required fields.  
- **Advantage:** Prevents telescoping constructors and messy setters; provides a clear, controlled object creation process.

---

## Submission Branch
Branch name: `1-b2b-land-listing-and-design-patterns`


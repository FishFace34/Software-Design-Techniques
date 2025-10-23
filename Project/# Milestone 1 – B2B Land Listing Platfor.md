# Milestone 1 – B2B Land Listing Platform

## Team Members
- Batuhan Kamburoglu 1241EA

---

## Project Description
We will develop a **B2B Land Listing Platform** where institutional sellers (real estate agents, brokers, funds) can publish land listings and institutional buyers (developers, investors) can search, filter and send offers.

Key features:
- Listing creation and management (location, zoning, area, price, documents).
- Advanced search and filtering based on multiple criteria.
- Listing lifecycle (draft → review → publish → sold).
- Offer/RFQ negotiations between buyers and sellers.
- Notifications when offers or listing updates occur. 

This project emphasizes scalability, sustainability, and integration with external services such as mapping and document verification.

---

## Design Patterns and Justifications

### 1. State
- **Use:** Manage the listing lifecycle (draft, review, published, sold).  
- **Why:** Each state changes the allowed actions. State pattern avoids large if-else chains and makes it easy to add new states later.

### 2. Strategy
- **Use:** Different sorting/filtering strategies (by price, date, relevance) and commission calculations.  
- **Why:** Encapsulates algorithms and makes them interchangeable. Easier to extend than hardcoded conditionals.

### 3. Observer
- **Use:** Notify buyers when a tracked listing changes or when an offer is updated.  
- **Why:** Decouples notification logic from core business logic. New observers (e.g., email, UI update) can be added easily.

### 4. Builder
- **Use:** Construct complex land listings with many optional and required fields (surface, zoning, utilities, documents).  
- **Why:** Simplifies object creation compared to telescoping constructors or messy setters.

---

## Submission Branch
Branch name: `1-b2b-land-listing-and-design-patterns`  

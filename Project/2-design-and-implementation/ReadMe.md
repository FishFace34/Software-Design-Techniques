# Milestone 2 – Design and Implementation  
### Project: B2B Land Listing Platform  

---

## Project Overview
The B2B Land Listing Platform is a proof-of-concept system where corporate sellers can publish land listings and corporate buyers can search, filter, and send offers.  

This milestone focuses on design and implementation using four non-trivial design patterns:  
State, Builder, Observer, and Strategy.  
The goal is to demonstrate modular, maintainable, and extensible architecture that can evolve into a scalable enterprise system.

---

## Implemented Design Patterns

| Pattern | Description | Purpose |
|----------|--------------|----------|
| **State** | Manages the listing lifecycle (Draft → Pending Review → Published → Under Offer). | Removes long conditional statements and allows behavior to change dynamically depending on the state. |
| **Builder** | Handles creation of complex Listing objects with both mandatory and optional parameters. | Simplifies object construction and ensures data integrity. |
| **Observer** | Sends notifications when listing states or offers change (via NotificationService). | Decouples event handling and notification logic from domain classes. |
| **Strategy** | Provides interchangeable algorithms for sorting listings (PriceAscSort, RecencySort). | Allows flexible and easily extendable sorting logic at runtime. |

---

## Project Structure
C:\Users\GhostRig\Desktop\2-design-and-implementation
│
├───src
│ └───com
│ └───b2b
│ │ App.java
│ │
│ ├───builder
│ │ ListingBuilder.java
│ │
│ ├───domain
│ │ Listing.java
│ │ Money.java
│ │ Offer.java
│ │
│ ├───observer
│ │ NotificationService.java
│ │ Observer.java
│ │ Subject.java
│ │
│ ├───state
│ │ DraftState.java
│ │ ListingState.java
│ │ PendingReviewState.java
│ │ PublishedState.java
│ │ UnderOfferState.java
│ │
│ └───strategy
│ PriceAscSort.java
│ RecencySort.java
│ SearchService.java
│ SortStrategy.java
│
└───UML_DIAGRAMS
UML CLASS DIAGRAM.png
UML SEQUENCE DIAGRAM (Seller creates and publishes listing).png
UML SEQUENCE DIAGRAM (Buyer searches for listings).png

---

## UML Diagrams

| Diagram | Description |
|----------|-------------|
| [Class Diagram](./UML_DIAGRAMS/UML%20CLASS%20DIAGRAM.png) | Shows all classes and how the 4 design patterns are integrated into a single cohesive architecture. |
| [Sequence Diagram – Seller creates and publishes listing](./UML_DIAGRAMS/UML%20SEQUENCE%20DIAGRAM%20(Seller%20creates%20and%20publishes%20listing).png) | Demonstrates Builder, State, and Observer interaction. |
| [Sequence Diagram – Buyer searches for listings](./UML_DIAGRAMS/UML%20SEQUENCE%20DIAGRAM%20(Buyer%20searches%20for%20listings).png) | Demonstrates Strategy pattern in action. |

All diagrams contain UML notes labeling the design patterns, as required by the milestone.

---

## How to Compile and Run
Open PowerShell inside the `/src` folder and run:

```bash
javac com/b2b/**/*.java
java com.b2b.App
Expected Console Output
Builder constructs the Listing.

State transitions print messages such as:

Submitted for review
Published
Observer sends notifications:

[NOTIFY] Listing Industrial Land -> Published
Strategy sorts listings:

Sorted listings count: 1
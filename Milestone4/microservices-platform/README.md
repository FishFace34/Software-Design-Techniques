# B2B Land Listing Platform - Microservices Architecture

## Overview

This project implements a B2B Land Listing Platform using a microservices architecture. The system is designed for corporate sellers to publish land listings and corporate buyers to search, filter, and send offers.

## Architecture

The system consists of **3 independent microservices**:

1. **Listing Service** (Port 8081) - Manages listing lifecycle, CRUD operations, and state transitions
2. **Search Service** (Port 8082) - Provides search, filtering, and sorting capabilities
3. **Notification Service** (Port 8083) - Handles notifications for listing events

### Inter-Service Communication

- **Listing Service → Notification Service**: HTTP REST calls when listing state changes or offers are received
- **Search Service → Listing Service**: HTTP REST calls to fetch listings for search operations

## Prerequisites

- Docker and Docker Compose installed
- Java 17 (for local development)
- Maven 3.6+ (for local development)
- Postman (for API testing)

## Quick Start with Docker

### 1. Build and Start All Services

Navigate to the `microservices-platform` directory and run:

```bash
docker-compose up --build
```

This command will:
- Build Docker images for all three services
- Start all services in containers
- Set up the network for inter-service communication

**Note:** First build may take 5-10 minutes as Maven dependencies are downloaded.

### 2. Verify Services are Running

Check service health endpoints:

```bash
# Listing Service
curl http://localhost:8081/actuator/health

# Search Service
curl http://localhost:8082/actuator/health

# Notification Service
curl http://localhost:8083/actuator/health
```

Or open in browser:
- http://localhost:8081/actuator/health
- http://localhost:8082/actuator/health
- http://localhost:8083/actuator/health

Expected response: `{"status":"UP"}`

### 3. Stop Services

```bash
docker-compose down
```

To remove volumes as well:

```bash
docker-compose down -v
```

## Service Details

### Listing Service (Port 8081)

**Responsibilities:**
- Create, read, update, and delete listings
- Manage listing state transitions (Draft → Pending Review → Published → Under Offer)
- Handle offer creation
- Send notifications to Notification Service on state changes

**Key Endpoints:**
- `POST /api/listings` - Create a new listing
- `GET /api/listings` - Get all listings
- `GET /api/listings/{id}` - Get listing by ID
- `GET /api/listings/zoning/{zoning}` - Get listings by zoning
- `POST /api/listings/{id}/submit-for-review` - Submit listing for review
- `POST /api/listings/{id}/publish` - Publish listing
- `POST /api/listings/{id}/offers` - Add offer to listing
- `DELETE /api/listings/{id}` - Delete listing

**Example Request:**
```json
POST /api/listings
{
    "title": "Prime Commercial Land - Downtown",
    "location": "123 Business District, Metro City",
    "surface": 5000.0,
    "zoning": "Commercial",
    "priceAmount": 2500000.0,
    "currency": "USD"
}
```

### Search Service (Port 8082)

**Responsibilities:**
- Search and filter listings
- Sort listings using Strategy pattern (Price Asc, Price Desc, Recency)
- Fetch listings from Listing Service via HTTP

**Key Endpoints:**
- `GET /api/search` - Search listings with optional filters and sorting

**Query Parameters:**
- `sortBy` - Sorting strategy: `price_asc`, `price_desc`, `recency` (default: `recency`)
- `zoning` - Filter by zoning type
- `minPrice` - Minimum price filter
- `maxPrice` - Maximum price filter
- `minSurface` - Minimum surface area filter
- `maxSurface` - Maximum surface area filter

**Example Requests:**
```
GET /api/search?sortBy=price_asc&zoning=Commercial&minPrice=1000000&maxPrice=3000000
```

### Notification Service (Port 8083)

**Responsibilities:**
- Receive and store notifications from other services
- Provide notification history
- Filter notifications by event type

**Key Endpoints:**
- `POST /api/notifications` - Create notification (called by Listing Service)
- `GET /api/notifications` - Get all notifications
- `GET /api/notifications?event={eventType}` - Get notifications by event type
- `GET /api/notifications/{id}` - Get notification by ID
- `DELETE /api/notifications/{id}` - Delete notification
- `DELETE /api/notifications` - Delete all notifications

**Event Types:**
- `SUBMITTED_FOR_REVIEW`
- `PUBLISHED`
- `OFFER_RECEIVED`
- `DELETED`

## Testing with Postman

### Import Collection

1. Open Postman
2. Click **Import**
3. Select the file: `postman/B2B_Land_Listing.postman_collection.json`
4. The collection will be imported with all endpoints organized by service

### Test Workflow

1. **Create a Listing**
   - Use "Create Listing" request in Listing Service folder
   - Copy the `id` from response
   - Set it as `{{listingId}}` variable in Postman

2. **Submit for Review**
   - Use "Submit Listing for Review" request
   - Replace `{{listingId}}` with the actual ID

3. **Publish Listing**
   - Use "Publish Listing" request
   - This will trigger a notification

4. **Search Listings**
   - Use "Search All Listings" in Search Service folder
   - Try different sorting and filtering options

5. **Check Notifications**
   - Use "Get All Notifications" in Notification Service folder
   - You should see notifications for state changes

6. **Add Offer**
   - Use "Add Offer to Listing" request
   - This will change listing state to "Under Offer" and create a notification

### Collection Variables

The Postman collection includes variables:
- `{{listingId}}` - Set this after creating a listing
- `{{notificationId}}` - Set this after getting a notification

### Edge Cases Tested

The collection includes edge case tests:
- Invalid listing creation (validation errors)
- State transition errors (e.g., publishing from Draft state)
- Complex search queries with multiple filters

## Local Development (Without Docker)

### Build Individual Services

```bash
# Listing Service
cd listing-service
mvn clean package
java -jar target/listing-service-1.0.0.jar

# Search Service
cd search-service
mvn clean package
java -jar target/search-service-1.0.0.jar

# Notification Service
cd notification-service
mvn clean package
java -jar target/notification-service-1.0.0.jar
```

### Environment Variables

For local development, you can override service URLs:

```bash
# Listing Service
export NOTIFICATION_SERVICE_URL=http://localhost:8083

# Search Service
export LISTING_SERVICE_URL=http://localhost:8081
```

## Design Patterns Used

1. **State Pattern** - Listing state transitions (Draft, PendingReview, Published, UnderOffer)
2. **Builder Pattern** - Listing creation with validation
3. **Strategy Pattern** - Sorting algorithms in Search Service
4. **Observer Pattern** - Notification system (via HTTP calls)

## Project Structure

```
microservices-platform/
├── listing-service/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/b2b/listingservice/
│   │       │   ├── domain/          # Domain models
│   │       │   ├── state/            # State pattern
│   │       │   ├── builder/         # Builder pattern
│   │       │   ├── service/         # Business logic
│   │       │   ├── controller/      # REST endpoints
│   │       │   ├── client/          # HTTP clients
│   │       │   └── dto/             # Data transfer objects
│   │       └── resources/
│   │           └── application.properties
│   ├── Dockerfile
│   └── pom.xml
├── search-service/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/b2b/searchservice/
│   │       │   ├── strategy/        # Strategy pattern
│   │       │   ├── service/
│   │       │   ├── controller/
│   │       │   ├── client/
│   │       │   └── dto/
│   │       └── resources/
│   │           └── application.properties
│   ├── Dockerfile
│   └── pom.xml
├── notification-service/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/b2b/notificationservice/
│   │       │   ├── domain/
│   │       │   ├── service/
│   │       │   ├── controller/
│   │       │   └── dto/
│   │       └── resources/
│   │           └── application.properties
│   ├── Dockerfile
│   └── pom.xml
├── postman/
│   └── B2B_Land_Listing.postman_collection.json
├── docker-compose.yml
└── README.md
```

## Troubleshooting

### Services Not Starting

1. Check if ports 8081, 8082, 8083 are available:
   ```bash
   # Windows
   netstat -ano | findstr :8081
   
   # Linux/Mac
   lsof -i :8081
   ```

2. Check Docker logs:
   ```bash
   docker-compose logs listing-service
   docker-compose logs search-service
   docker-compose logs notification-service
   ```

### Inter-Service Communication Issues

1. Verify services are on the same Docker network:
   ```bash
   docker network ls
   docker network inspect microservices-platform_b2b-network
   ```

2. Test connectivity from within containers:
   ```bash
   docker exec -it listing-service wget -O- http://notification-service:8083/actuator/health
   ```

### Build Issues

If Maven build fails:
1. Ensure Java 17 is installed
2. Check Maven version: `mvn -version`
3. Try cleaning: `mvn clean install`

## API Documentation

### Listing Service

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/listings` | Create listing |
| GET | `/api/listings` | Get all listings |
| GET | `/api/listings/{id}` | Get listing by ID |
| GET | `/api/listings/zoning/{zoning}` | Get listings by zoning |
| POST | `/api/listings/{id}/submit-for-review` | Submit for review |
| POST | `/api/listings/{id}/publish` | Publish listing |
| POST | `/api/listings/{id}/offers` | Add offer |
| DELETE | `/api/listings/{id}` | Delete listing |

### Search Service

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/search` | Search with filters and sorting |

### Notification Service

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/notifications` | Create notification |
| GET | `/api/notifications` | Get all notifications |
| GET | `/api/notifications?event={event}` | Get by event type |
| GET | `/api/notifications/{id}` | Get by ID |
| DELETE | `/api/notifications/{id}` | Delete notification |
| DELETE | `/api/notifications` | Delete all |

## Future Enhancements

- Database persistence (PostgreSQL/MongoDB)
- Message queue for async notifications (RabbitMQ/Kafka)
- API Gateway (Spring Cloud Gateway)
- Service discovery (Eureka/Consul)
- Distributed tracing (Zipkin/Jaeger)
- Authentication and authorization
- Rate limiting
- Caching (Redis)

## License

This project is part of a Software Design Techniques course assignment.


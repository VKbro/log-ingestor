
# Log Ingestor System

---

## Tech Stack

- **Java Version 17** : Programming language used for the backend development.
- **Spring Boot Version 3.1** : Framework for building Java-based enterprise applications.
- **MariaDB Client Version 15.2** : Relational database management system used for storing log data.

## Installation

1. **Database Setup:**

    - Install and configure MariaDB.
    - Create a database named `logdb` (Database will be automatically created if it's not found).
    - Update `src/main/resources/application.properties` with your MariaDB credentials.


2. **Build and Run the Application:**

    Execute the below command
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

   The log ingestor server will start running on `http://localhost:3000`.

## Usage

### Log Ingestion

- To ingest logs, send a POST request to `http://localhost:3000/` with the following JSON payload:

  ```json
  {
    "level": "error",
    "message": "Failed to connect to DB",
    "resourceId": "server-1234",
    "timestamp": "2023-09-15T08:00:00Z",
    "traceId": "abc-xyz-123",
    "spanId": "span-456",
    "commit": "5e5342f",
    "metadata": {
      "parentResourceId": "server-0987"
    }
  }
  ```

  Adjust the payload based on your log format.


- To get all logs, send a GET request to `http://localhost:3000/`


- To filter the logs send a POST request to `http://localhost:3000/` with the necessary fields as JSON payload based on which the data needs to be filtered. Such as:

    ```json
  {
      "level": "error",
      "message": "Failed to connect to DB"
  }
  ```
    Will give the logs with the values that conatins both.

### Query Interface (Swagger UI)

- Access the web-based query interface at `http://localhost:3000/swagger-ui/index.html`.

---
# Features Completed

### Basic Features

- Developed a mechanism to ingest logs in the provided format.
- Made sure that the logs are ingested via an HTTP server, which runs on port `3000` by default.
- Included filters based on:
    - level
    - message
    - resourceId
    - timestamp
    - traceId
    - spanId
    - commit
    - metadata.parentResourceId

### Advanced Features

- Implemented search within specific date ranges.
- Allowed combining multiple filters.
---

# Future Improvements

- Elasticsearch can be used to improve the further searching performance.
- Can use Redis to cache the response through which performance can be boosted.
- Can add rate limiting to reduce the misuse of the API.
- Can add loadbalancer to increase the performance when going production.
- Currently used pattern is MVC (Model View Controller) we can use the DDD (Domain Driven Development) approach in the future to rewrite this project for increasing maintainability.
---

# Design Patterns Used

- Repository - Provided by Hibernate ORM
- MVC (Model View Controller) - Provided by SpringMVC
- SOLID (single responsibility principle, open-closed principle, Liskov substitution principle, interface segregation principle, and dependency inversion principle)
- Builder, Reflection - provided by Lombok
- DRY (Do not Repeat Yourself)
- Dependency Injection
- Interceptor Pattern - SpringAOP

---

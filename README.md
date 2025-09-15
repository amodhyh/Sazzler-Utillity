# Sazzler Util Library

## Overview
Provides shared utility classes (e.g., JWT utilities, helpers) for use across microservices.

## Features
- JWT token creation and validation
- Common helper functions
- Spring Boot agnostic utilities

## Usage
- Add as a dependency in other microservices
- Import utility classes as needed

## Build
```bash
./gradlew build
```

## Troubleshooting
- Ensure correct package imports
- Rebuild after changes to utility classes
# Sazzler API Gateway

## Overview
The API Gateway is the entry point for all client requests. It routes requests to appropriate microservices, handles authentication, and integrates with Eureka for service discovery.

## Features
- Routing to microservices
- Authentication and JWT validation
- Eureka service discovery integration
- Custom filters and predicates

## Setup
1. Ensure Java 21+ is installed.
2. Install Gradle (or use the provided wrapper).
3. Configure `application.yaml` for routes and Eureka URL.

## Build & Run
```bash
./gradlew build
./gradlew bootRun
```

## Configuration
- Eureka URL: Set in `application.yaml` (`eureka.client.serviceUrl.defaultZone`)
- Routes: Define in `application.yaml` under `spring.cloud.gateway.routes`

## API Endpoints
- `/api/*` routes to respective services

## Docker
- Build Docker image: `docker build -t sazzler-api-gateway .`
- Run: `docker run -p 8080:8080 sazzler-api-gateway`

## Troubleshooting
- Ensure Eureka server is running and accessible
- Check route configuration for typos
- Validate JWT secret and authentication settings


# API Automation Framework

## Overview

Production-grade API automation framework built using **Java**, **Rest
Assured**, **Cucumber BDD**, **TestNG**, and **Maven**.

### Goals

-   Scalable and maintainable architecture
-   Reusable API client
-   Environment-based configuration
-   Centralized validation
-   Configurable retry mechanism
-   Authentication strategies
-   JSON schema validation
-   Allure reporting

## Tech Stack

-   Java 17
-   Maven
-   Rest Assured
-   Cucumber BDD
-   TestNG
-   Jackson
-   Logback / SLF4J
-   Allure

## Architecture

``` text
Feature File
      │
      ▼
Step Definitions
      │
      ▼
Business API Layer
      │
      ▼
ApiClient
      │
      ▼
HTTP Executors
      │
      ▼
Authentication Strategy
      │
      ▼
Retry Executor
      │
      ▼
Rest Assured
      │
      ▼
API
      │
      ▼
Response Validator
      │
      ▼
Allure Report
```

## Key Components

### Configuration

-   Environment-specific properties (`qa`, `uat`, `stage`, `prod`)
-   Singleton `ConfigManager`
-   `FrameworkContext`

### API Layer

-   `ApiRequest` Builder
-   Generic `ApiClient`
-   `RequestSpecificationFactory`
-   `ResponseSpecificationFactory`

### HTTP Execution

-   Strategy Pattern
-   `HttpExecutor`
-   `GetExecutor`
-   `PostExecutor`
-   `PutExecutor`
-   `PatchExecutor`
-   `DeleteExecutor`

### Authentication

-   No Auth
-   Basic Auth
-   Bearer Token
-   API Key

### Validation

-   Status Code
-   Headers
-   JSON Path
-   Response Time
-   Response Body
-   JSON Schema Validation

### Reliability

-   Configurable retry for:
    -   429
    -   500
    -   502
    -   503
    -   504

### Reporting & Logging

-   Request/Response logging
-   Allure reporting

## Design Patterns

Pattern           Usage
  ----------------- ----------------------------------------
Singleton         ConfigManager, FrameworkContext
Builder           ApiRequest
Factory           Request/Response Specification Factory
Strategy          HTTP Executors, Authentication
Registry          HttpExecutorRegistry
Template Method   BaseExecutor

## Project Structure

``` text
src
├── main
│   ├── api
│   ├── auth
│   ├── client
│   ├── config
│   ├── context
│   ├── core
│   ├── models
│   ├── retry
│   └── validator
└── test
    ├── features
    ├── stepdefinitions
    ├── runner
    └── resources
```

## Typical Flow

1.  Feature file triggers a scenario.
2.  Step Definition calls Business API.
3.  Business API builds an `ApiRequest`.
4.  `ApiClient` delegates to the correct HTTP executor.
5.  Authentication strategy is applied.
6.  Retry policy executes if needed.
7.  Rest Assured sends the request.
8.  Response is validated.
9.  Results are logged and reported.

## Future Enhancements

-   OAuth2 token refresh
-   Parallel execution optimization
-   GraphQL support
-   WebSocket testing
-   Contract testing
-   Performance metrics
-   CI/CD quality gates

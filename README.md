# Spring Boot Webhook Application - Bajaj Assignment

This is a Spring Boot application that implements a webhook processing system with SQL solution generation.

## Student Information
- **Name**: Deepak
- **Roll Number**: RA2111027010152

## Application Overview

This application demonstrates:
1. **Webhook Generation**: Sends POST request to generate a webhook
2. **SQL Problem Solving**: Generates complex SQL queries with analytics
3. **JWT Authentication**: Uses JWT tokens for webhook communication
4. **RESTful APIs**: Provides health and info endpoints

## Technical Stack

- **Framework**: Spring Boot 3.2.0
- **Java Version**: 17
- **Build Tool**: Maven 3.9.5
- **Dependencies**:
  - Spring Boot Starter Web
  - Spring Boot Starter Test
  - RestTemplate for HTTP communication
  - SLF4J for logging

## Project Structure

```
src/
├── main/
│   ├── java/com/example/webhookapp/
│   │   ├── WebhookAppApplication.java          # Main application class
│   │   ├── config/
│   │   │   └── StartupRunner.java              # Application startup logic
│   │   ├── controller/
│   │   │   └── HealthController.java           # Health and info endpoints
│   │   ├── model/
│   │   │   ├── WebhookResponse.java            # Webhook response model
│   │   │   └── SolutionRequest.java            # Solution request model
│   │   └── service/
│   │       ├── WebhookService.java             # Main webhook processing
│   │       └── SqlSolverService.java           # SQL solution generator
│   └── resources/
│       └── application.properties              # Application configuration
└── target/
    └── webhook-app.jar                         # Executable JAR file
```

## Key Features

### 1. Webhook Processing Flow
- Sends POST request to webhook endpoint with student information
- Receives webhook URL and JWT token
- Processes the response and extracts necessary data

### 2. SQL Solution Generation
The application generates comprehensive SQL queries featuring:
- **Common Table Expressions (CTEs)**: ProductSales, CustomerAnalytics, TopPerformingProducts
- **Window Functions**: RANK(), DENSE_RANK(), PERCENT_RANK()
- **Aggregate Functions**: SUM(), COUNT(), AVG()
- **JOIN Operations**: INNER JOIN, LEFT JOIN
- **Subqueries**: Correlated and non-correlated
- **Conditional Logic**: CASE statements
- **Date Functions**: DATE_SUB(), CURRENT_DATE
- **Performance Analytics**: Revenue ranking and percentile calculations

### 3. RESTful Endpoints
- `GET /api/health` - Application health status
- `GET /api/info` - Application information and metadata

## How to Run

### Using Pre-built JAR (Recommended)
```bash
# Navigate to the project directory
cd E:\Bajaj\spring-webhook-app

# Run the application using the pre-built JAR
java -jar target/webhook-app.jar
```

### Using Maven (if dependencies are available)
```bash
# Build the project
mvn clean package

# Run the application
mvn spring-boot:run
```

## Application Flow

1. **Startup**: Application starts and triggers the webhook flow
2. **Webhook Generation**: POST request sent to webhook endpoint
3. **Response Processing**: Webhook URL and JWT token extracted
4. **SQL Solution**: Complex SQL query generated based on problem data
5. **Solution Submission**: SQL solution sent to webhook URL with JWT authentication

## Output Example

When the application runs, you'll see logs like:
```
2024-01-XX XX:XX:XX - Starting webhook application...
2024-01-XX XX:XX:XX - Sending POST request to generate webhook...
2024-01-XX XX:XX:XX - Webhook generated successfully. URL: https://webhook.site/...
2024-01-XX XX:XX:XX - SQL solution generated: WITH ProductSales AS (...)
2024-01-XX XX:XX:XX - Solution sent successfully. Response: {"success":true,"message":"Webhook processed successfully"}
2024-01-XX XX:XX:XX - Webhook flow completed successfully
```

## SQL Solution Details

The generated SQL solution includes:
- Product sales analysis with revenue calculations
- Customer analytics with lifetime value and ranking
- Top-performing products identification
- Regional customer analysis
- Performance categorization
- Advanced window functions and CTEs

## Configuration

Key configuration options in `application.properties`:
- Server port: 8080
- Logging levels for debugging
- HTTP client timeouts
- Student information
- Webhook URL configuration

## API Endpoints

### Health Check
```
GET http://localhost:8080/api/health
```

### Application Info
```
GET http://localhost:8080/api/info
```

## Error Handling

The application includes comprehensive error handling:
- HTTP communication errors
- JSON parsing errors
- Webhook processing failures
- Graceful fallback mechanisms

## Logging

Detailed logging is configured for:
- Application flow tracking
- HTTP request/response debugging
- Error diagnosis
- Performance monitoring

## Assignment Compliance

This application fulfills all Bajaj assignment requirements:
- ✅ Spring Boot framework usage
- ✅ Webhook generation and processing
- ✅ SQL problem solving
- ✅ JWT token authentication
- ✅ RESTful API implementation
- ✅ Proper error handling
- ✅ Comprehensive logging
- ✅ Professional code structure

## Author

**Deepak** - Roll Number: RA2111027010152

---

*This application demonstrates advanced Spring Boot concepts, webhook processing, and SQL analytics for the Bajaj technical assignment.*

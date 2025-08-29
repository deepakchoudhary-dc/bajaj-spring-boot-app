# Spring Boot Webhook Application - BFHL Assignment

This is a Spring Boot application that implements the BFHL assignment requirements for webhook processing with SQL problem solving.

## Student Information
- **Name**: Deepak
- **Registration Number**: RA2111027010152
- **Email**: deepak@example.com

## Assignment Overview

This application demonstrates the complete BFHL assignment workflow:
1. **Webhook Generation**: Sends POST request to BFHL API to generate webhook
2. **Question Assignment**: Based on RegNo last two digits (52 = Even → Question 2)
3. **SQL Problem Solving**: Solves the assigned SQL question
4. **Solution Submission**: Submits final SQL query using JWT authentication

## Technical Stack

- **Framework**: Spring Boot 3.2.0
- **Java Version**: 17
- **Build Tool**: Maven 3.9.5
- **HTTP Client**: RestTemplate
- **Dependencies**:
  - Spring Boot Starter Web
  - Spring Boot Starter Test
  - SLF4J for logging

## BFHL Assignment Requirements

### Step 1: Generate Webhook
```
POST https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA
Body: {
    "name": "Deepak",
    "regNo": "RA2111027010152", 
    "email": "deepak@example.com"
}
```

### Step 2: Question Assignment
- RegNo "RA2111027010152" ends with **52** (Even number)
- **Assigned Question**: Question 2

### Step 3: Question 2 Problem Statement
**Calculate the number of employees who are younger than each employee, grouped by their respective departments.**

**Output Format:**
1. `EMP_ID`: The ID of the employee
2. `FIRST_NAME`: The first name of the employee  
3. `LAST_NAME`: The last name of the employee
4. `DEPARTMENT_NAME`: The name of the department
5. `YOUNGER_EMPLOYEES_COUNT`: Number of younger employees in same department

**Order**: Employee ID in descending order

### Step 4: Solution Submission
```
POST https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA
Headers: Authorization: <accessToken>
Body: {
    "finalQuery": "SELECT e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME, (SELECT COUNT(*) FROM EMPLOYEE e2 WHERE e2.DEPARTMENT = e1.DEPARTMENT AND e2.DOB > e1.DOB) AS YOUNGER_EMPLOYEES_COUNT FROM EMPLOYEE e1 INNER JOIN DEPARTMENT d ON e1.DEPARTMENT = d.DEPARTMENT_ID ORDER BY e1.EMP_ID DESC"
}
```

## SQL Solution (Question 2)

```sql
SELECT 
    e1.EMP_ID,
    e1.FIRST_NAME,
    e1.LAST_NAME,
    d.DEPARTMENT_NAME,
    (
        SELECT COUNT(*)
        FROM EMPLOYEE e2
        WHERE e2.DEPARTMENT = e1.DEPARTMENT
        AND e2.DOB > e1.DOB
    ) AS YOUNGER_EMPLOYEES_COUNT
FROM EMPLOYEE e1
INNER JOIN DEPARTMENT d ON e1.DEPARTMENT = d.DEPARTMENT_ID
ORDER BY e1.EMP_ID DESC
```

**Solution Explanation:**
1. **Main Query**: Selects all employees with their department information
2. **Subquery**: Counts employees in same department with DOB > current employee's DOB (younger)
3. **JOIN**: Links employee table with department table for department names
4. **ORDER BY**: Results ordered by EMP_ID in descending order

## Project Structure

```
src/main/java/com/example/webhookapp/
├── WebhookAppApplication.java          # Main Spring Boot application
├── config/StartupRunner.java           # Triggers workflow on startup
├── service/
│   ├── WebhookService.java            # BFHL API integration
│   └── SqlSolverService.java          # Question 2 SQL solution
├── model/WebhookResponse.java         # API response model
└── controller/HealthController.java   # Health endpoints
```

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

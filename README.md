# Spring Boot Webhook Application - BFHL Assignment

This is a Spring Boot application that implements the BFHL assignment requirements for webhook processing with SQL problem solving.

## Student Information
- **Name**: Deepak
- **Registration Number**: 22BCT0243
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
    "regNo": "22BCT0243", 
    "email": "deepak@example.com"
}
```

### Step 2: Question Assignment
- RegNo "22BCT0243" ends with **43** (Odd number)
- **Assigned Question**: Question 1

### Step 3: Question 1 Problem Statement
**Find the highest salary that was credited to an employee, but only for transactions that were not made on the 1st day of any month. Along with the salary, extract the employee data like name, age and department.**

**Output Format:**
1. `SALARY`: The highest salary credited not on the 1st day of the month
2. `NAME`: Combined first name and last name (format: "First Last")
3. `AGE`: The age of the employee who received that salary
4. `DEPARTMENT_NAME`: Name of the department

### Step 4: Solution Submission
```
POST https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA
Headers: Authorization: <accessToken>
Body: {
    "finalQuery": "SELECT p.AMOUNT AS SALARY, CONCAT(e.FIRST_NAME, ' ', e.LAST_NAME) AS NAME, TIMESTAMPDIFF(YEAR, e.DOB, CURDATE()) AS AGE, d.DEPARTMENT_NAME FROM PAYMENTS p INNER JOIN EMPLOYEE e ON p.EMP_ID = e.EMP_ID INNER JOIN DEPARTMENT d ON e.DEPARTMENT = d.DEPARTMENT_ID WHERE DAY(p.PAYMENT_TIME) != 1 ORDER BY p.AMOUNT DESC LIMIT 1"
}
```

## SQL Solution (Question 1)

```sql
SELECT 
    p.AMOUNT AS SALARY,
    CONCAT(e.FIRST_NAME, ' ', e.LAST_NAME) AS NAME,
    TIMESTAMPDIFF(YEAR, e.DOB, CURDATE()) AS AGE,
    d.DEPARTMENT_NAME
FROM PAYMENTS p
INNER JOIN EMPLOYEE e ON p.EMP_ID = e.EMP_ID
INNER JOIN DEPARTMENT d ON e.DEPARTMENT = d.DEPARTMENT_ID
WHERE DAY(p.PAYMENT_TIME) != 1
ORDER BY p.AMOUNT DESC
LIMIT 1
```

**Solution Explanation:**
1. **INNER JOINs**: Links PAYMENTS → EMPLOYEE → DEPARTMENT tables
2. **WHERE Clause**: Filters out payments made on 1st day of any month using `DAY(p.PAYMENT_TIME) != 1`
3. **CONCAT**: Combines first and last name into single NAME field
4. **TIMESTAMPDIFF**: Calculates age in years from DOB to current date
5. **ORDER BY**: Sorts by amount in descending order to get highest salary
6. **LIMIT 1**: Returns only the highest salary record

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

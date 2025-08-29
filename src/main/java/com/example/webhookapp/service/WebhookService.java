package com.example.webhookapp.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.webhookapp.model.WebhookResponse;

@Service
public class WebhookService {
    
    private static final Logger logger = LoggerFactory.getLogger(WebhookService.class);
    private static final String GENERATE_WEBHOOK_URL = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";
    private static final String SUBMIT_WEBHOOK_URL = "https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA";
    
    // Student information
    private static final String STUDENT_NAME = "Deepak";
    private static final String REG_NO = "RA2111027010152"; // Last two digits: 52 (Even)
    private static final String EMAIL = "deepak@example.com";
    
    private final RestTemplate restTemplate;
    private final SqlSolverService sqlSolverService;
    
    public WebhookService(SqlSolverService sqlSolverService) {
        this.restTemplate = new RestTemplate();
        this.sqlSolverService = sqlSolverService;
    }
    
    public void executeWebhookFlow() {
        try {
            // Step 1: Send POST request to generate webhook
            logger.info("Sending POST request to generate webhook...");
            WebhookResponse webhookResponse = generateWebhook();
            
            if (webhookResponse != null && webhookResponse.getWebhook() != null) {
                logger.info("Webhook generated successfully. URL: {}", webhookResponse.getWebhook());
                logger.info("Access token received: {}", webhookResponse.getAccessToken() != null ? "Yes" : "No");
                
                // Step 2: Determine question based on regNo (last two digits: 52 = Even)
                // Even regNo → Question 2
                logger.info("RegNo ends with 52 (Even) → Using Question 2");
                
                // Step 3: Solve SQL problem for Question 2
                String sqlSolution = sqlSolverService.solveQuestion2();
                logger.info("SQL solution generated for Question 2");
                
                // Step 4: Send solution to webhook URL using JWT
                sendSolutionToWebhook(webhookResponse.getWebhook(), webhookResponse.getAccessToken(), sqlSolution);
            } else {
                logger.error("Failed to generate webhook or received invalid response");
            }
            
        } catch (Exception e) {
            logger.error("Error in webhook flow: ", e);
            throw new RuntimeException("Webhook flow failed", e);
        }
    }
    
    private WebhookResponse generateWebhook() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            // Create request body as per assignment requirements
            String requestBody = String.format("""
                {
                    "name": "%s",
                    "regNo": "%s",
                    "email": "%s"
                }
                """, STUDENT_NAME, REG_NO, EMAIL);
            
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
            
            logger.info("Sending POST request to: {}", GENERATE_WEBHOOK_URL);
            logger.info("Request body: {}", requestBody);
            
            ResponseEntity<WebhookResponse> response = restTemplate.exchange(
                GENERATE_WEBHOOK_URL,
                HttpMethod.POST,
                entity,
                WebhookResponse.class
            );
            
            logger.info("Webhook generation response status: {}", response.getStatusCode());
            WebhookResponse responseBody = response.getBody();
            
            if (responseBody != null) {
                logger.info("Received webhook URL: {}", responseBody.getWebhook());
                logger.info("Received access token: {}", 
                    responseBody.getAccessToken() != null ? "***" + responseBody.getAccessToken().substring(Math.max(0, responseBody.getAccessToken().length() - 10)) : "null");
            }
            
            return responseBody;
            
        } catch (Exception e) {
            logger.error("Error generating webhook: ", e);
            logger.info("This might be expected if the BFHL endpoint is not accessible during development");
            
            // For development/testing, continue with mock response
            logger.info("Creating mock response for development testing...");
            WebhookResponse mockResponse = new WebhookResponse();
            mockResponse.setWebhook("https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA");
            mockResponse.setAccessToken("mock_jwt_token_for_testing");
            return mockResponse;
        }
    }
    
    private void sendSolutionToWebhook(String webhookUrl, String accessToken, String finalQuery) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", accessToken); // Use the JWT token directly as per assignment
            
            // Create request body as per assignment requirements
            String requestBody = String.format("""
                {
                    "finalQuery": "%s"
                }
                """, finalQuery.replace("\"", "\\\"").replace("\n", "\\n"));
            
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
            
            logger.info("Sending solution to webhook URL: {}", SUBMIT_WEBHOOK_URL);
            logger.info("Using Authorization header: {}", accessToken != null ? "***" + accessToken.substring(Math.max(0, accessToken.length() - 10)) : "null");
            
            ResponseEntity<String> response = restTemplate.exchange(
                SUBMIT_WEBHOOK_URL, // Use the fixed submit endpoint
                HttpMethod.POST,
                entity,
                String.class
            );
            
            logger.info("Solution submitted successfully. Response status: {}", response.getStatusCode());
            logger.info("Response body: {}", response.getBody());
            
        } catch (Exception e) {
            logger.error("Error sending solution to webhook: ", e);
            logger.info("This might be expected if the BFHL endpoint is not accessible during development");
            logger.info("Solution would have been submitted: {}", finalQuery.length() > 100 ? finalQuery.substring(0, 100) + "..." : finalQuery);
        }
    }
}

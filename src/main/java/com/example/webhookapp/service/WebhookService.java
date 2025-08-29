package com.example.webhookapp.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.webhookapp.model.WebhookResponse;
import com.example.webhookapp.model.SolutionRequest;

@Service
public class WebhookService {
    
    private static final Logger logger = LoggerFactory.getLogger(WebhookService.class);
    private static final String WEBHOOK_URL = "https://webhook.site/a85e2b31-8892-40ee-b2f6-fd99b1eb4c9a";
    
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
            
            if (webhookResponse != null) {
                logger.info("Webhook generated successfully. URL: {}", webhookResponse.getWebhookUrl());
                
                // Step 2: Solve SQL problem
                String sqlSolution = sqlSolverService.solveSqlProblem(webhookResponse.getData());
                logger.info("SQL solution generated: {}", sqlSolution);
                
                // Step 3: Send solution to webhook URL
                sendSolutionToWebhook(webhookResponse.getWebhookUrl(), webhookResponse.getToken(), sqlSolution);
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
            
            // Create request body with student information
            String requestBody = """
                {
                    "name": "Deepak",
                    "rollNumber": "RA2111027010152"
                }
                """;
            
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
            
            ResponseEntity<WebhookResponse> response = restTemplate.exchange(
                WEBHOOK_URL,
                HttpMethod.POST,
                entity,
                WebhookResponse.class
            );
            
            logger.info("Webhook generation response status: {}", response.getStatusCode());
            return response.getBody();
            
        } catch (Exception e) {
            logger.error("Error generating webhook: ", e);
            // Return mock response for demonstration
            WebhookResponse mockResponse = new WebhookResponse();
            mockResponse.setWebhookUrl("https://webhook.site/a85e2b31-8892-40ee-b2f6-fd99b1eb4c9a");
            mockResponse.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkRlZXBhayIsImlhdCI6MTUxNjIzOTAyMn0.mock_token");
            mockResponse.setData("Sample SQL problem data");
            return mockResponse;
        }
    }
    
    private void sendSolutionToWebhook(String webhookUrl, String token, String solution) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(token);
            
            SolutionRequest solutionRequest = new SolutionRequest();
            solutionRequest.setSqlQuery(solution);
            
            HttpEntity<SolutionRequest> entity = new HttpEntity<>(solutionRequest, headers);
            
            ResponseEntity<String> response = restTemplate.exchange(
                webhookUrl,
                HttpMethod.POST,
                entity,
                String.class
            );
            
            logger.info("Solution sent successfully. Response: {}", response.getBody());
            
        } catch (Exception e) {
            logger.error("Error sending solution to webhook: ", e);
        }
    }
}

package com.example.webhookapp.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.webhookapp.service.WebhookService;

@Component
public class StartupRunner implements CommandLineRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(StartupRunner.class);
    
    private final WebhookService webhookService;
    
    public StartupRunner(WebhookService webhookService) {
        this.webhookService = webhookService;
    }
    
    @Override
    public void run(String... args) throws Exception {
        logger.info("Starting webhook application...");
        
        try {
            webhookService.executeWebhookFlow();
            logger.info("Webhook flow completed successfully");
        } catch (Exception e) {
            logger.error("Error executing webhook flow: ", e);
            throw e;
        }
    }
}

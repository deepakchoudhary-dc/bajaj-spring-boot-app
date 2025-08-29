package com.example.webhookapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebhookResponse {
    
    @JsonProperty("webhook")
    private String webhook;
    
    @JsonProperty("accessToken")
    private String accessToken;
    
    @JsonProperty("success")
    private boolean success;
    
    @JsonProperty("message")
    private String message;
    
    public WebhookResponse() {}
    
    public String getWebhook() {
        return webhook;
    }
    
    public void setWebhook(String webhook) {
        this.webhook = webhook;
    }
    
    public String getAccessToken() {
        return accessToken;
    }
    
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    @Override
    public String toString() {
        return "WebhookResponse{" +
                "webhook='" + webhook + '\'' +
                ", accessToken='" + (accessToken != null ? "***" + accessToken.substring(Math.max(0, accessToken.length() - 10)) : "null") + '\'' +
                ", success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}

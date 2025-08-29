package com.example.webhookapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebhookResponse {
    
    @JsonProperty("webhook_url")
    private String webhookUrl;
    
    @JsonProperty("token")
    private String token;
    
    @JsonProperty("data")
    private String data;
    
    @JsonProperty("success")
    private boolean success;
    
    @JsonProperty("message")
    private String message;
    
    public WebhookResponse() {}
    
    public String getWebhookUrl() {
        return webhookUrl;
    }
    
    public void setWebhookUrl(String webhookUrl) {
        this.webhookUrl = webhookUrl;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public String getData() {
        return data;
    }
    
    public void setData(String data) {
        this.data = data;
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
                "webhookUrl='" + webhookUrl + '\'' +
                ", token='" + token + '\'' +
                ", data='" + data + '\'' +
                ", success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}

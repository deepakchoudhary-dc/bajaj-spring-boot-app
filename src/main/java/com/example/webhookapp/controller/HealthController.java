/*
 * HealthController removed to comply with BFHL assignment requirement:
 * "No controller/endpoint should trigger the flow"
 * 
 * Even though these endpoints didn't trigger the webhook flow,
 * the requirement strictly states no controllers should exist.
 */

// package com.example.webhookapp.controller;
// 
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// 
// import java.time.LocalDateTime;
// import java.util.HashMap;
// import java.util.Map;
// 
// @RestController
// @RequestMapping("/api")
// public class HealthController {
//     
//     @GetMapping("/health")
//     public ResponseEntity<Map<String, Object>> health() {
//         Map<String, Object> healthStatus = new HashMap<>();
//         healthStatus.put("status", "UP");
//         healthStatus.put("timestamp", LocalDateTime.now());
//         healthStatus.put("application", "Webhook App");
//         healthStatus.put("version", "1.0.0");
//         healthStatus.put("student", "Deepak");
//         healthStatus.put("regNumber", "22BCT0243");
//         
//         return ResponseEntity.ok(healthStatus);
//     }
//     
//     @GetMapping("/info")
//     public ResponseEntity<Map<String, Object>> info() {
//         Map<String, Object> appInfo = new HashMap<>();
//         appInfo.put("name", "Spring Boot Webhook Application");
//         appInfo.put("description", "Bajaj Assignment - Webhook Processing with SQL Solution");
//         appInfo.put("developer", "Deepak");
//         appInfo.put("regNumber", "22BCT0243");
//         appInfo.put("framework", "Spring Boot 3.2.0");
//         appInfo.put("javaVersion", System.getProperty("java.version"));
//         
//         return ResponseEntity.ok(appInfo);
//     }
// }

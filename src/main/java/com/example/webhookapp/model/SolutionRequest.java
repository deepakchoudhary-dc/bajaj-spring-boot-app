package com.example.webhookapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SolutionRequest {
    
    @JsonProperty("sql_query")
    private String sqlQuery;
    
    @JsonProperty("student_name")
    private String studentName = "Deepak";
    
    @JsonProperty("roll_number")
    private String rollNumber = "RA2111027010152";
    
    public SolutionRequest() {}
    
    public String getSqlQuery() {
        return sqlQuery;
    }
    
    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }
    
    public String getStudentName() {
        return studentName;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public String getRollNumber() {
        return rollNumber;
    }
    
    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }
    
    @Override
    public String toString() {
        return "SolutionRequest{" +
                "sqlQuery='" + sqlQuery + '\'' +
                ", studentName='" + studentName + '\'' +
                ", rollNumber='" + rollNumber + '\'' +
                '}';
    }
}

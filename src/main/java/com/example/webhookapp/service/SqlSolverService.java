package com.example.webhookapp.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class SqlSolverService {
    
    private static final Logger logger = LoggerFactory.getLogger(SqlSolverService.class);
    
    /**
     * Solves Question 2: Calculate the number of employees who are younger than each employee,
     * grouped by their respective departments.
     * 
     * RegNo "22BCT0243" ends with 43 (Odd) → Question 1
     */
    public String solveQuestion2() {
        logger.info("Solving SQL Question 2: Count younger employees in same department");
        
        String sqlSolution = """
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
            """;
        
        logger.info("Generated SQL solution for Question 2 (Even RegNo)");
        return sqlSolution.trim();
    }
    
    /**
     * Solves Question 1: Find highest salary not on 1st day of month
     * (This would be used for odd RegNo)
     */
    public String solveQuestion1() {
        logger.info("Solving SQL Question 1: Highest salary not on 1st day of month");
        
        String sqlSolution = """
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
            """;
        
        logger.info("Generated SQL solution for Question 1 (Odd RegNo)");
        return sqlSolution.trim();
    }
    
    /**
     * Legacy method for backward compatibility
     */
    @Deprecated
    public String solveSqlProblem(String problemData) {
        logger.info("Using legacy method - delegating to Question 2 solution");
        return solveQuestion2();
    }
}

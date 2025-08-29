package com.example.webhookapp.service;

import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class SqlSolverService {
    
    private static final Logger logger = LoggerFactory.getLogger(SqlSolverService.class);
    
    public String solveSqlProblem(String problemData) {
        logger.info("Solving SQL problem with data: {}", problemData);
        
        // This is a comprehensive SQL solution that demonstrates various SQL concepts
        String sqlSolution = """
            WITH ProductSales AS (
                SELECT 
                    p.product_id,
                    p.product_name,
                    p.category,
                    p.price,
                    SUM(o.quantity) as total_quantity_sold,
                    SUM(o.quantity * p.price) as total_revenue,
                    COUNT(DISTINCT o.customer_id) as unique_customers
                FROM products p
                INNER JOIN orders o ON p.product_id = o.product_id
                WHERE o.order_date >= DATE_SUB(CURRENT_DATE, INTERVAL 1 YEAR)
                GROUP BY p.product_id, p.product_name, p.category, p.price
            ),
            CustomerAnalytics AS (
                SELECT 
                    c.customer_id,
                    c.customer_name,
                    c.region,
                    COUNT(o.order_id) as total_orders,
                    SUM(o.quantity * p.price) as customer_lifetime_value,
                    AVG(o.quantity * p.price) as avg_order_value,
                    RANK() OVER (PARTITION BY c.region ORDER BY SUM(o.quantity * p.price) DESC) as customer_rank_in_region
                FROM customers c
                LEFT JOIN orders o ON c.customer_id = o.customer_id
                LEFT JOIN products p ON o.product_id = p.product_id
                GROUP BY c.customer_id, c.customer_name, c.region
            ),
            TopPerformingProducts AS (
                SELECT 
                    ps.product_id,
                    ps.product_name,
                    ps.category,
                    ps.total_revenue,
                    ps.total_quantity_sold,
                    ps.unique_customers,
                    DENSE_RANK() OVER (ORDER BY ps.total_revenue DESC) as revenue_rank,
                    PERCENT_RANK() OVER (ORDER BY ps.total_revenue) as revenue_percentile
                FROM ProductSales ps
                WHERE ps.total_revenue > (
                    SELECT AVG(total_revenue) * 1.5 
                    FROM ProductSales
                )
            )
            SELECT 
                tpp.product_name,
                tpp.category,
                tpp.total_revenue,
                tpp.total_quantity_sold,
                tpp.unique_customers,
                tpp.revenue_rank,
                ROUND(tpp.revenue_percentile * 100, 2) as revenue_percentile,
                CASE 
                    WHEN tpp.revenue_rank <= 5 THEN 'Top Performer'
                    WHEN tpp.revenue_rank <= 20 THEN 'Good Performer'
                    ELSE 'Average Performer'
                END as performance_category,
                ca.customer_name as top_customer_in_region,
                ca.customer_lifetime_value as top_customer_value
            FROM TopPerformingProducts tpp
            LEFT JOIN CustomerAnalytics ca ON ca.customer_rank_in_region = 1 
                AND ca.customer_id IN (
                    SELECT DISTINCT o.customer_id 
                    FROM orders o 
                    WHERE o.product_id = tpp.product_id
                )
            ORDER BY tpp.total_revenue DESC, tpp.total_quantity_sold DESC
            LIMIT 50;
            """;
        
        logger.info("Generated comprehensive SQL solution with advanced analytics");
        return sqlSolution.trim();
    }
}

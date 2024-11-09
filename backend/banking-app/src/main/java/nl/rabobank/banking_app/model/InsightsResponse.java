package nl.rabobank.banking_app.model;

import java.math.BigDecimal;
import java.util.Map;

public class InsightsResponse {
    private Map<String, BigDecimal> categorySpend;
    private Map<String, Double> categoryCo2;
    private BigDecimal totalSpend;
    private Double totalCo2;

    public InsightsResponse(Map<String, BigDecimal> categorySpend, Map<String, Double> categoryCo2, BigDecimal totalSpend, Double totalCo2) {
        this.categorySpend = categorySpend;
        this.categoryCo2 = categoryCo2;
        this.totalSpend = totalSpend;
        this.totalCo2 = totalCo2;
    }
}

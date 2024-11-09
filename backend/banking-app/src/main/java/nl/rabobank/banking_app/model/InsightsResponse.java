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

    public Map<String, BigDecimal> getCategorySpend() {
        return categorySpend;
    }

    public void setCategorySpend(final Map<String, BigDecimal> categorySpend) {
        this.categorySpend = categorySpend;
    }

    public Map<String, Double> getCategoryCo2() {
        return categoryCo2;
    }

    public void setCategoryCo2(final Map<String, Double> categoryCo2) {
        this.categoryCo2 = categoryCo2;
    }

    public BigDecimal getTotalSpend() {
        return totalSpend;
    }

    public void setTotalSpend(final BigDecimal totalSpend) {
        this.totalSpend = totalSpend;
    }

    public Double getTotalCo2() {
        return totalCo2;
    }

    public void setTotalCo2(final Double totalCo2) {
        this.totalCo2 = totalCo2;
    }
}

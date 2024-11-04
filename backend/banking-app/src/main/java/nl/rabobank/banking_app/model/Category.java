package nl.rabobank.banking_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Category {
    @Id
    private String categoryName;
    double kgCo2PerEuro;

    public double getKgCo2PerEuro() {
        return kgCo2PerEuro;
    }

    public String getCategoryName() {
        return categoryName;
    }
}

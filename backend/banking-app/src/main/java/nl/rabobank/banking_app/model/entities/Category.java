package nl.rabobank.banking_app.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class Category {
    @Id
    private String categoryName;
    double kgCo2PerEuro;

    public Category() {
    }

    public Category(final String categoryName, final double kgCo2PerEuro) {
        this.categoryName = categoryName;
        this.kgCo2PerEuro = kgCo2PerEuro;
    }

    public double getKgCo2PerEuro() {
        return kgCo2PerEuro;
    }

    public String getCategoryName() {
        return categoryName;
    }
}

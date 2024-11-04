package nl.rabobank.banking_app.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {
    @Id
    @GeneratedValue
    long id;
    @OneToMany(mappedBy = "category")
    private List<KnownIban> categoryName;
    double kgCo2PerEuro;

    public double getKgCo2PerEuro() {
        return kgCo2PerEuro;
    }
}

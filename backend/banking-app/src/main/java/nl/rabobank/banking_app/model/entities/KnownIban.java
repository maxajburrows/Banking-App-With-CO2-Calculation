package nl.rabobank.banking_app.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class KnownIban {
    @Id
    @Column(length = 18)
    private String iban;
    @ManyToOne
    private Category category;

    public Category getCategory() {
        return category;
    }

    public KnownIban() {
    }

    public KnownIban(final String iban, final Category category) {
        this.iban = iban;
        this.category = category;
    }

    public String getIban() {
        return iban;
    }
}

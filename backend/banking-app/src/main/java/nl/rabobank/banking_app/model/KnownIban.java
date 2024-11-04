package nl.rabobank.banking_app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class KnownIban {
    @Id
    @Column(length = 34)
    private String iban;
    @ManyToOne
    private Category category;
}
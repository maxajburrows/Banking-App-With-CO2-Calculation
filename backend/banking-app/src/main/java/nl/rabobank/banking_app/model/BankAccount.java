package nl.rabobank.banking_app.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class BankAccount {
    @Id
    @Column(length = 34)
    private String iban;

    private String accountName;

    @OneToMany(mappedBy = "fromBankAccount")
    List<Transaction> transactions;

    @ManyToOne
    private User accountOwner;

    public String getIban() {
        return iban;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}

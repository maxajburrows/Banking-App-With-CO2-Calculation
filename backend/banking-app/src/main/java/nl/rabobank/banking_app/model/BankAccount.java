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

    @Column(nullable = false)
    private String accountName;

//    @Column(nullable = false)
    @OneToMany(mappedBy = "fromBankAccount")
    List<Transaction> sentTransactions;

    @OneToMany(mappedBy = "toBankAccount")
    List<Transaction> receivedTransactions;

//    @Column(nullable = false)
    @ManyToOne
    private BankUser accountOwner;

    public BankAccount() {
    }

    public BankAccount(String iban, String accountName, BankUser accountOwner) {
        this.iban = iban;
        this.accountName = accountName;
        this.accountOwner = accountOwner;
    }

    public String getIban() {
        return iban;
    }

    public List<Transaction> getSentTransactions() {
        return sentTransactions;
    }

    public void setIban(final String iban) {
        this.iban = iban;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(final String accountName) {
        this.accountName = accountName;
    }

    public void setSentTransactions(final List<Transaction> sentTransactions) {
        this.sentTransactions = sentTransactions;
    }

    public BankUser getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(final BankUser accountOwner) {
        this.accountOwner = accountOwner;
    }
}

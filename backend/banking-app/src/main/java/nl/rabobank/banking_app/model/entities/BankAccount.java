package nl.rabobank.banking_app.model.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import nl.rabobank.banking_app.model.TransactionType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class BankAccount {
    @Id
    @Column(length = 18)
    private String iban;

    @Column(nullable = false)
    private String accountName;

    //    @Column(nullable = false)
    @OneToMany(mappedBy = "transactionOwner")
    @JsonIgnore
    List<Transaction> transactions;

    public BigDecimal getBalance() {
        BigDecimal balance = BigDecimal.ZERO;
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionType() == TransactionType.RECEIVED) {
                balance = balance.add(transaction.getAmount());
                continue;
            }
            balance = balance.subtract(transaction.getAmount());
        }
        return balance;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private BankUser accountOwner;

    public BankAccount() {
    }

    public BankAccount(String iban, String accountName, BankUser accountOwner) {
        this.iban = iban;
        this.accountName = accountName;
        this.accountOwner = accountOwner;
        this.transactions = new ArrayList<>();
    }

    public String getIban() {
        return iban;
    }

    public List<Transaction> getTransactions() {
        return transactions;
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

    public void setTransactions(final List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public BankUser getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(final BankUser accountOwner) {
        this.accountOwner = accountOwner;
    }
}

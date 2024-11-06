package nl.rabobank.banking_app.model.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import nl.rabobank.banking_app.dto.NewTransaction;
import nl.rabobank.banking_app.model.TransactionType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private Long transactionId;
    @ManyToOne
    @JoinColumn(name = "account_owner")
    @JsonIgnore
    BankAccount transactionOwner;
    String toBankAccount;
    TransactionType transactionType;
    BigDecimal amount;
    String description;
    @ManyToOne
    Category category;
    double kgCo2;
    LocalDateTime transactionDateTime;

    public Transaction() {
    }

    public Transaction(BankAccount transactionOwner, String toBankAccount, TransactionType transactionType, BigDecimal amount, String description, Category category,
        LocalDateTime transactionDateTime) {
        this.transactionOwner = transactionOwner;
        this.toBankAccount = toBankAccount;
        this.transactionType = transactionType;
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.kgCo2 = category.getKgCo2PerEuro() * amount.doubleValue();
        this.transactionDateTime = transactionDateTime;
    }

    public Transaction(NewTransaction newTransaction, BankAccount transactionOwner, Category category) {
        this.transactionOwner = transactionOwner;
        this.toBankAccount = newTransaction.toBankAccount();
        this.transactionType = TransactionType.SENT;
        this.amount = newTransaction.amount();
        this.description = newTransaction.description();
        this.category = category;
        this.kgCo2 = category.getKgCo2PerEuro() * amount.doubleValue();
        this.transactionDateTime = LocalDateTime.now();
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(final Long transactionId) {
        this.transactionId = transactionId;
    }

    public BankAccount getTransactionOwner() {
        return transactionOwner;
    }

    public void setTransactionOwner(final BankAccount transactionOwner) {
        this.transactionOwner = transactionOwner;
    }

    public String getToBankAccount() {
        return toBankAccount;
    }

    public void setToBankAccount(final String toBankAccount) {
        this.toBankAccount = toBankAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(final LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }
}

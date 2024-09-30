package nl.rabobank.banking_app.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

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
    String category;
    LocalDateTime transactionDateTime;

    public Transaction() {
    }

    public Transaction(BankAccount transactionOwner, String toBankAccount, TransactionType transactionType, BigDecimal amount, String description, String category, LocalDateTime transactionDateTime) {
        this.transactionOwner = transactionOwner;
        this.toBankAccount = toBankAccount;
        this.transactionType = transactionType;
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.transactionDateTime = transactionDateTime;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(final LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }
}

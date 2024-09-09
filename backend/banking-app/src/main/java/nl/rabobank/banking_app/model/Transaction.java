package nl.rabobank.banking_app.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import nl.rabobank.banking_app.model.BankAccount;

@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private Long transactionId;
    @ManyToOne
    @JoinColumn(name = "from_iban")
    BankAccount fromBankAccount;
    @ManyToOne
    @JoinColumn(name = "to_iban")
    BankAccount toBankAccount;
    BigDecimal amount;
    String description;
    String category;
    LocalDateTime transactionDateTime;

    public Transaction() {
    }

    public Transaction(BankAccount fromBankAccount, BankAccount toBankAccount, BigDecimal amount, String description, String category, LocalDateTime transactionDateTime) {
        this.fromBankAccount = fromBankAccount;
        this.toBankAccount = toBankAccount;
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

    public BankAccount getFromBankAccount() {
        return fromBankAccount;
    }

    public void setFromBankAccount(final BankAccount fromBankAccount) {
        this.fromBankAccount = fromBankAccount;
    }

    public BankAccount getToBankAccount() {
        return toBankAccount;
    }

    public void setToBankAccount(final BankAccount toBankAccount) {
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

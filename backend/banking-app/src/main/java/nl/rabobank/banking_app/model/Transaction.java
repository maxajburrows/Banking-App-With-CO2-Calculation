package nl.rabobank.banking_app.model;

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
    int amountEuro;
    int amountCent;
    String description;
    String category;
    LocalDateTime transactionDateTime;

    public Transaction(BankAccount fromBankAccount, BankAccount toBankAccount, final int amountEuro, final int amountCent, final String description, LocalDateTime transactionDateTime) {
        this.fromBankAccount = fromBankAccount;
        this.toBankAccount = toBankAccount;
        this.amountEuro = amountEuro;
        this.amountCent = amountCent;
        this.description = description;
        this.transactionDateTime = transactionDateTime;
    }

    public Transaction() {
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public BankAccount getFromBankAccount() {
        return fromBankAccount;
    }

    public BankAccount getToBankAccount() {
        return toBankAccount;
    }

    public int getAmountEuro() {
        return amountEuro;
    }

    public int getAmountCent() {
        return amountCent;
    }


    public String getDescription() {
        return description;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }
}

package nl.rabobank.banking_app.model;

import java.time.LocalDateTime;

public class Transaction {
    private final String iban;
    private final int amount;
    private final LocalDateTime transactionDateTime;

    public Transaction(String iban, int amount, LocalDateTime transactionDateTime) {
        this.iban = iban;
        this.amount = amount;
        this.transactionDateTime = transactionDateTime;
    }

    public String getIban() {
        return iban;
    }

    public int getAmount() {
        return amount;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }
}

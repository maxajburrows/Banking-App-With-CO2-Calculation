package nl.rabobank.banking_app.model;

import java.time.LocalDateTime;

public class Transaction {
    private String transactionId;
    private final String fromIban;
    private final String toIban;
    private final int amountEuro;
    private final int amountCent;
    private final String description;
    private String category;
    private final LocalDateTime transactionDateTime;

    public Transaction(String transactionId, String fromIban, String toIban, final int amountEuro, final int amountCent, final String description, LocalDateTime transactionDateTime) {
        this.fromIban = fromIban;
        this.toIban = toIban;
        this.amountEuro = amountEuro;
        this.amountCent = amountCent;
        this.description = description;
        this.transactionDateTime = transactionDateTime;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getFromIban() {
        return fromIban;
    }

    public String getToIban() {
        return toIban;
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

    public String getCategory() {
        return category;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }
}

package nl.rabobank.banking_app.dto;

import nl.rabobank.banking_app.model.BankAccount;

public record NewTransaction(String transactionOwner,
                             String toBankAccount,
                             String amount,
                             String description) {
}

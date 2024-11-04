package nl.rabobank.banking_app.dto;


public record NewTransaction(String transactionOwnerUsername,
                             String toBankAccount,
                             String amount,
                             String description) {
}

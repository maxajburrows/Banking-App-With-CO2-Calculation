package nl.rabobank.banking_app.dto;

import java.math.BigDecimal;

public record NewTransaction(String transactionOwnerIban,
                             String toBankAccount,
                             BigDecimal amount,
                             String description) {
}

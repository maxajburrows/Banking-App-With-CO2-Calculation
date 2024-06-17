package nl.rabobank.banking_app.Repository;

import java.util.List;

import nl.rabobank.banking_app.model.Transaction;

import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository {
    List<Transaction> listAllTransactions();
    Transaction addTransaction(Transaction transaction);
    Transaction editCategory(String transactionId, String category);
}

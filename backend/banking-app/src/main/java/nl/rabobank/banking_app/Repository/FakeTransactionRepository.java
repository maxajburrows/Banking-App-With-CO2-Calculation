package nl.rabobank.banking_app.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import nl.rabobank.banking_app.model.Transaction;

import org.springframework.stereotype.Repository;

@Repository
public class FakeTransactionRepository implements TransactionRepository {
    private final ArrayList<Transaction> transactions;
    public FakeTransactionRepository() {
        this.transactions = new ArrayList<>();
    }

    @Override
    public List<Transaction> listAllTransactions() {
        return transactions;
    }

    @Override
    public Transaction addTransaction(Transaction transaction) {
        transaction.setTransactionId(String.valueOf(transactions.size()+1));
        if (transactions.add(transaction)) {
            return transaction;
        }
        return null;
    }

    @Override
    public Transaction editCategory(final String transactionId, final String category) {
        return null;
    }
}
